package id.web.dedekurniawan.newsapp.presentation.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import id.web.dedekurniawan.newsapp.R
import id.web.dedekurniawan.newsapp.core.Result
import id.web.dedekurniawan.newsapp.domain.model.News

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseNewsListScreen(
    title: String,
    retrieve: (String?) -> Unit,
    newsListResult: Result<List<News>>,
    onNewsClick: (News) -> Unit,
    onBookmarkClick: (News) -> Unit,
    onBookmarkIconClick: (() -> Unit)? = null
) {
    var searchQuery by remember { mutableStateOf("") }

    // load pertama kali
    LaunchedEffect(Unit) {
        retrieve(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Search $title...") },
                        singleLine = true,
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                retrieve(searchQuery.ifBlank { null })
                            }
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent
                        )
                    )
                },
                actions = {
                    if (onBookmarkIconClick!=null) {
                        IconButton(onClick = onBookmarkIconClick) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_bookmarked),
                                contentDescription = "Bookmarks"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (newsListResult) {
                is Result.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {
                    val news = newsListResult.data ?: emptyList()
                    LazyColumn {
                        items(news.size) { index ->
                            NewsItem(
                                news = news[index],
                                onClick = { onNewsClick(news[index]) },
                                onBookmarkClick = { clickedNews ->
                                    onBookmarkClick(clickedNews)
                                }
                            )
                        }
                    }
                }

                is Result.Error -> {
                    ErrorView("Error: ${newsListResult.message}")

                }
            }
        }
    }
}

@Composable
fun NewsItem(
    news: News,
    onClick: () -> Unit,
    onBookmarkClick: (News) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = news.imageUrl,
                    error = painterResource(R.drawable.ic_broken_image),
                    placeholder = painterResource(R.drawable.ic_default_image)
                ),
                contentScale = ContentScale.Fit,
                contentDescription = news.title,
                modifier = Modifier
                    .width(160.dp)
                    .height(80.dp)
                    .padding(end = 8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = news.sourceName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = news.publishedAt ?: "",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = {
                onBookmarkClick(news)
            }) {
                Icon(
                    painter = painterResource(id = if (news.isBookmarked) R.drawable.ic_bookmarked else R.drawable.ic_bookmark),
                    contentDescription = if (news.isBookmarked) "Remove Bookmark" else "Add Bookmark"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNewsItem() {
    val news = News(
        title = "Dummy Title",
        description = "Lorem ipsum dolor sit amet...",
        author = "Author Name",
        sourceName = "News Source",
        url = "https://extratv.com/2025/08/23/jerry-adler-veteran-actor-of-sopranos-fame-dies-at-96/",
        imageUrl = null,
        publishedAt = "2025-08-25",
        content = "This is a dummy news for preview."
    )
    NewsItem(news, onClick = {}){
    }
}