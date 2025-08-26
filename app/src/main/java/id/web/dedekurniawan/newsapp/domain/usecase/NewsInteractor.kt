package id.web.dedekurniawan.newsapp.domain.usecase

import android.graphics.Bitmap
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.domain.repository.NewsRepository

class NewsInteractor(private val repository: NewsRepository): NewsUseCase {
    override fun retrieveNews(query: String?) = repository.retrieveNews(query)

    override fun retrieveBookmarkNews(query: String?) = repository.retrieveBookmarkNews(query)

    override fun handleBookmarkTrigger(news: News, bitmap: Bitmap?) {
        val isBookmarked = !news.isBookmarked
        if (isBookmarked) {
            repository.saveToBookmark(news)
//            bitmap?.let { localStorage.saveNewsImage(news.id, it) }
        } else {
            repository.deleteFromBookmark(news)
//            localStorage.deleteNewsImage(news.id)
        }
        news.isBookmarked = isBookmarked
    }
}