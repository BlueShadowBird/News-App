package id.web.dedekurniawan.newsapp.data.local.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.web.dedekurniawan.newsapp.data.local.database.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM bookmark_news")
    suspend fun retrieveFavorites(): List<NewsEntity>

    @Query("SELECT * FROM bookmark_news WHERE title LIKE '%' || :query || '%'")
    fun searchNewsFlow(query: String): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsEntity)

    @Delete
    suspend fun deleteNews(news: NewsEntity)
}