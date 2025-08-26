package id.web.dedekurniawan.newsapp.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.presentation.screen.common.BaseNewsListScreen
import id.web.dedekurniawan.newsapp.presentation.viewmodel.NewsViewModel

@Composable
fun MainNewsScreen(
    viewModel: NewsViewModel,
    onNewsClick: (News) -> Unit,
    onNavigateBookmark: () -> Unit
) {
    val newsListResult by viewModel.newsListResult.observeAsState(Result.Loading())

    BaseNewsListScreen(
        title = "News",
        retrieve = { query -> viewModel.retrieveNews(query) },
        newsListResult = newsListResult,
        onNewsClick = onNewsClick,
        onBookmarkClick = { news -> viewModel.handleBookmarkTrigger(news) },
        onBookmarkIconClick = onNavigateBookmark
    )
}