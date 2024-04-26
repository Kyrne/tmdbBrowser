package com.m7019e.tmdbbrowser.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.m7019e.tmdbbrowser.network.TMDBApiService
import com.m7019e.tmdbbrowser.utils.Constants
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit


interface AppContainer {
    val moviesRepository: MoviesRepository
    val savedMovieRepository: SavedMovieRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {

    fun getLoggerInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    val movieDBJson = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(
            okhttp3.OkHttpClient.Builder()
                .addInterceptor(getLoggerInterceptor())
                .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(movieDBJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(Constants.TMDB_API_MOVIE_BASE_URL)
        .build()

    private val retrofitService: TMDBApiService by lazy {
        retrofit.create(TMDBApiService::class.java)
    }

    override val moviesRepository: MoviesRepository by lazy {
        NetworkMoviesRepository(retrofitService)
    }

    override val savedMovieRepository: SavedMovieRepository by lazy {
        FavoriteMoviesRepository(MovieDatabase.getDatabase(context).movieDao())
    }
}