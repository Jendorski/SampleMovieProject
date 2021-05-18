
package com.example.samplemoviesproject.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplemoviesproject.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for [Movie]
 */
@Dao
interface MoviesDao {

    /**
     * Inserts [movies] into the [Movie.TABLE_NAME] table.
     * Duplicate values are replaced in the table.
     * @param movies
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    /**
     * Deletes all the posts from the [Movie.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${Movie.TABLE_NAME}")
    suspend fun deleteAllMovies()

    /**
     * Fetches the post from the [Movie.TABLE_NAME] table whose id is [movieId].
     * @param movieId Unique ID of [Movie]
     * @return [Flow] of [Movie] from database table.
     */
    @Query("SELECT * FROM ${Movie.TABLE_NAME} WHERE ID = :movieId")
    fun getMovieById(movieId: Int): Flow<Movie>

    /**
     * Fetches all the posts from the [Movie.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Movie.TABLE_NAME}")
    fun getAllMovies(): Flow<List<Movie>>
}
