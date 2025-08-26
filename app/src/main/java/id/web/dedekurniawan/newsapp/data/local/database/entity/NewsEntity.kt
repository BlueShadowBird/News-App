package id.web.dedekurniawan.newsapp.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="bookmark_news")
class NewsEntity(
    @PrimaryKey
    val url: String,
    val title: String,
    val description: String?,
    val author: String?,
    val sourceName: String,
    val imageUrl: String?,
    val publishedAt: String?,
    val content: String?
)