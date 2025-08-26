package id.web.dedekurniawan.newsapp.presentation.screen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import id.web.dedekurniawan.newsapp.R
import id.web.dedekurniawan.newsapp.domain.model.News

@Composable
fun NewsDetailScreen(
    news: News,
    onBookmarkClick: (News) -> Unit
) {
    var isBookmarked by remember { mutableStateOf(news.isBookmarked) }

    Column(modifier = Modifier.fillMaxSize()) {

        // Custom Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Title berita (expandable, multi-line)
            Text(
                text = news.title,
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f), // biar bisa melar
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            // Tombol Bookmark
            IconButton(onClick = {
                onBookmarkClick(news)
                isBookmarked = news.isBookmarked
            }) {
                Icon(
                    painter = painterResource(
                        id = if (isBookmarked) R.drawable.ic_bookmarked else R.drawable.ic_bookmark
                    ),
                    contentDescription = if (isBookmarked) "Remove Bookmark" else "Add Bookmark",
                    tint = Color.White
                )
            }
        }

        // Konten detail
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = news.imageUrl,
                    error = painterResource(R.drawable.ic_default_image),
                    placeholder = painterResource(R.drawable.ic_default_image)
                ),
                contentDescription = news.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "By ${news.author} | ${news.sourceName}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = news.content ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewNewsDetail() {
    val news = News(
        title = "Ini adalah judul yang sangat panjang sekali sampai bisa 2 baris",
        description = "Lorem ipsum dolor sit amet...",
        author = "Author Name",
        sourceName = "News Source",
        url = "https://extratv.com/2025/08/23/jerry-adler-veteran-actor-of-sopranos-fame-dies-at-96/",
        imageUrl = null,
        publishedAt = "2025-08-25",
        content = "This is a dummy news for preview."
    )
    NewsDetailScreen(news){

    }
}