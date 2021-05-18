
package com.example.samplemoviesproject.data.remote.api

import com.example.samplemoviesproject.model.BaseResponseModel
import com.example.samplemoviesproject.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to fetch Foodium posts using dummy end point [THE_MOVIES_DB_API_URL].
 */
interface NetworkService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String,
                          @Query("language") language: String): Response<BaseResponseModel<List<Movie>>>

    companion object {
        const val THE_MOVIES_DB_API_URL = "https://api.themoviedb.org/3/"
        const val THE_MOVIES_DB_API_KEY = "ee9dd4f9bac8c5261e8f33f262a2ba50"
        const val THE_MOVIES_DB_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    }
}
