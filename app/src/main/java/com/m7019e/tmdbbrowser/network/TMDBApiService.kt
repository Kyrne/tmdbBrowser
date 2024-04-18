package com.m7019e.tmdbbrowser.network

import com.m7019e.tmdbbrowser.model.GenreResponse
import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.model.MovieResponse
import com.m7019e.tmdbbrowser.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApiService {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("{id}")
    suspend fun getMovieDetails(
        @Path("id")
        id: String,
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): Movie

    @GET(Constants.TMDB_MOVIE_GENRE_URL)
    suspend fun getGenreList(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): GenreResponse
}