package id.web.dedekurniawan.newsapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import id.web.dedekurniawan.newsapp.domain.model.News
import id.web.dedekurniawan.newsapp.presentation.screen.bookmark.BookmarkNewsScreen
import id.web.dedekurniawan.newsapp.presentation.screen.common.NewsDetailScreen
import id.web.dedekurniawan.newsapp.presentation.screen.main.MainNewsScreen
import id.web.dedekurniawan.newsapp.presentation.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsNavGraph(navController: NavHostController) {
    val viewModel: NewsViewModel = koinViewModel()
    NavHost(navController, startDestination = "main") {
        composable("main") {
            MainNewsScreen(
                viewModel = viewModel,
                onNewsClick = { news ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("news", news)
                    navController.navigate("detail")
                },
                onNavigateBookmark = { navController.navigate("bookmark") }
            )
        }
        composable("bookmark") {
            BookmarkNewsScreen(
                viewModel = viewModel,
                onNewsClick = { news ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("news", news)
                    navController.navigate("detail")
                }
            )
        }
        composable("detail") {
            val news =
                navController.previousBackStackEntry?.savedStateHandle?.get<News>("news")
            news?.let {
                NewsDetailScreen(it) { news ->
                    viewModel.handleBookmarkTrigger(it)
                }
            }
        }
    }
}