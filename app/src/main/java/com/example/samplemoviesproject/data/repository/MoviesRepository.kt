
package com.example.samplemoviesproject.data.repository

import androidx.annotation.MainThread
import com.example.samplemoviesproject.data.local.dao.MoviesDao
import com.example.samplemoviesproject.data.remote.api.NetworkService
import com.example.samplemoviesproject.data.remote.api.NetworkService.Companion.THE_MOVIES_DB_API_KEY
import com.example.samplemoviesproject.model.BaseResponseModel
import com.example.samplemoviesproject.model.Movie
import com.example.samplemoviesproject.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton repository for fetching data from remote and storing it in database
 * for offline capability. This is Single source of data.
 */
@ExperimentalCoroutinesApi
@Singleton
class MoviesRepository @Inject constructor(
    private val moviesDao: MoviesDao,
    private val networkService: NetworkService
) {

    /**
     * Fetched the posts from network and stored it in database. At the end, data from persistence
     * storage is fetched and emitted.
     */
    fun getAllMovies(): Flow<State<List<Movie>>> {
        return object : NetworkBoundRepository<List<Movie>, BaseResponseModel<List<Movie>>>() {

            override suspend fun saveRemoteData(response: BaseResponseModel<List<Movie>>) =
                moviesDao.insertMovies(response.results!!)

            override fun fetchFromLocal(): Flow<List<Movie>> = moviesDao.getAllMovies()

            override suspend fun fetchFromRemote(): Response<BaseResponseModel<List<Movie>>> = networkService.getMovies(apiKey = THE_MOVIES_DB_API_KEY, language = "en-US")
        }.asFlow()
    }

    /**
     * Retrieves a post with specified [movieID].
     * @param movieID Unique id of a [Movie].
     * @return [Movie] data fetched from the database.
     */
    @MainThread
    fun getMovieById(movieID: Int): Flow<Movie> = moviesDao.getMovieById(movieID)
}
