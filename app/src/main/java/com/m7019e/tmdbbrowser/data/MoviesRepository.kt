package com.m7019e.tmdbbrowser.data

import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.model.MovieResponse
import com.m7019e.tmdbbrowser.network.TMDBApiService

interface MoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
    suspend fun getMovieDetails(movie: Movie): Movie
    suspend fun getGenreList(): Map<Int, String>
}

class NetworkMoviesRepository(private val apiService: TMDBApiService) : MoviesRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): MovieResponse {
        return apiService.getTopRatedMovies()
    }

    override suspend fun getMovieDetails(movie: Movie): Movie {
        return apiService.getMovieDetails(movie.id.toString())
    }

    override suspend fun getGenreList(): Map<Int, String> {
        return apiService.getGenreList().genres.associate {
            it.id to it.name
        }
    }

}