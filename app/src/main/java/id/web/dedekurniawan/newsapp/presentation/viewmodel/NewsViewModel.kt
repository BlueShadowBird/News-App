package id.web.dedekurniawan.newsapp.presentation.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel(private val useCase: NewsUseCase): ViewModel() {
    private val _newsListResult = MediatorLiveData<Result<List<News>>>()
    val newsListResult: LiveData<Result<List<News>>> = _newsListResult

    fun retrieveNews(query: String? = null){
        viewModelScope.launch {
            _newsListResult.addSource(useCase.retrieveNews(query).asLiveData()){
                _newsListResult.value = it
            }
        }
    }

    private val _bookmarkNewsListResult = MediatorLiveData<Result<List<News>>>()
    val bookmarkNewsListResult: LiveData<Result<List<News>>> = _bookmarkNewsListResult

    fun retrieveBookmarkNews(query: String? = null){
        viewModelScope.launch {
            _bookmarkNewsListResult.addSource(useCase.retrieveBookmarkNews(query).asLiveData()){
                _bookmarkNewsListResult.value = it
            }
        }
    }

    fun handleBookmarkTrigger(news: News, bitmap: Bitmap? = null) {
        viewModelScope.launch {
            useCase.handleBookmarkTrigger(news, bitmap)
        }
    }
}