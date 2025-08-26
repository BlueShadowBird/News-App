package id.web.dedekurniawan.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val title: String,
    val description: String?,
    val author: String?,
    val sourceName: String,
    val url: String,
    val imageUrl: String?,
    val publishedAt: String?,
    val content: String?,
    var isBookmarked: Boolean = false
): Parcelable