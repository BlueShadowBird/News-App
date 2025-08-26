package id.web.dedekurniawan.newsapp.presentation.screen.bookmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.presentation.screen.common.BaseNewsListScreen
import id.web.dedekurniawan.newsapp.presentation.viewmodel.NewsViewModel

@Composable
fun BookmarkNewsScreen(
    viewModel: NewsViewModel,
    onNewsClick: (News) -> Unit
) {
    val newsListResult by viewModel.bookmarkNewsListResult.observeAsState(Result.Loading())

    BaseNewsListScreen(
        title = "Bookmark",
        retrieve = { query -> viewModel.retrieveBookmarkNews(query) },
        newsListResult = newsListResult,
        onNewsClick = onNewsClick,
        onBookmarkClick = { news -> viewModel.handleBookmarkTrigger(news) },
    )
}
