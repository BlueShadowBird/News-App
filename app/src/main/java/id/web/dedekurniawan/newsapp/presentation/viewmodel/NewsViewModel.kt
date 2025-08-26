package id.web.dedekurniawan.newsapp.presentation.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModel(private val useCase: NewsUseCase): ViewModel() {
    private val queryNews = MutableStateFlow<String?>(null)

    val newsListResult: LiveData<Result<List<News>>> = queryNews
        .flatMapLatest { useCase.retrieveNews(it) }
        .asLiveData()

    fun retrieveNews(query: String?) {
        this.queryNews.value = query
    }


    private val queryBookmark = MutableStateFlow<String?>(null)

    val bookmarkNewsListResult: LiveData<Result<List<News>>> = queryBookmark
        .flatMapLatest { useCase.retrieveBookmarkNews(it) }
        .asLiveData()

    fun retrieveBookmarkNews(query: String?) {
        this.queryBookmark.value = query
    }

    fun handleBookmarkTrigger(news: News, bitmap: Bitmap? = null) {
        viewModelScope.launch {
            useCase.handleBookmarkTrigger(news, bitmap)
        }
    }

}