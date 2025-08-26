package id.web.dedekurniawan.newsapp.domain.usecase

import android.graphics.Bitmap
import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun retrieveNews(query: String?): Flow<Result<List<News>>>

    fun retrieveBookmarkNews(query: String?): Flow<Result<List<News>>>
    fun handleBookmarkTrigger(news: News, bitmap: Bitmap?)
}