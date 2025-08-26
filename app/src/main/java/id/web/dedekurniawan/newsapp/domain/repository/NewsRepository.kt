package id.web.dedekurniawan.newsapp.domain.repository

import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun retrieveNews(query: String?): Flow<Result<List<News>>>

    fun retrieveBookmarkNews(query: String?): Flow<Result<List<News>>>
    fun saveToBookmark(news: News)
    fun deleteFromBookmark(news: News)
}