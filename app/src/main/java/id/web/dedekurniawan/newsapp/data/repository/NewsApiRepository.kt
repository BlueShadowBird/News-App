package id.web.dedekurniawan.newsapp.data.repository

import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.data.local.database.room.NewsDao
import id.web.dedekurniawan.newsapp.data.mapper.toDomainModel
import id.web.dedekurniawan.newsapp.data.mapper.toEntityClass
import id.web.dedekurniawan.newsapp.data.remote.ApiService
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.collections.map

class NewsApiRepository(
    private val apiService: ApiService,
    private val dao: NewsDao
): NewsRepository {
    override fun retrieveNews(query: String?) = flow {
        emit(Result.Loading())
        try {
            val response = apiService.retrieveNews(query)
            if(response.status == "ok"){
                if(response.totalResults>0){
                    emitAll(
                        combine(
                            flow{ emit(response.news.map { it.toDomainModel() }) },
                            dao.searchNewsFlow(query?:"")
                        ) { newsFromApi, bookmarks ->
                            val bookmarkIds = bookmarks.map { it.url }.toSet()
                            newsFromApi.map { news ->
                                news.copy(isBookmarked = bookmarkIds.contains(news.url))
                            }
                        }.map { Result.Success(it) }
                    )
                }else{
                    emit(Result.Error("Produk tidak ditemukan"))
                }
            }else{
                emit(Result.Error(response.errorCode.toString()))
            }
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun retrieveBookmarkNews(query: String?) = flow {
        emit(Result.Loading())
        try {
            val aaaa = dao.searchNewsFlow(query?:"")
            emitAll(
                combine(
                    flow{ emit(aaaa.first().map { it.toDomainModel() }) },
                    aaaa
                ) { newsFromApi, bookmarks ->
                    val bookmarkIds = bookmarks.map { it.url }.toSet()
                    newsFromApi.map { news ->
                        news.copy(isBookmarked = bookmarkIds.contains(news.url))
                    }
                }.map { Result.Success(it) }
            )
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun saveToBookmark(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertNews(news.toEntityClass())
        }
    }

    override fun deleteFromBookmark(news: News) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteNews(news.toEntityClass())
        }
    }
}