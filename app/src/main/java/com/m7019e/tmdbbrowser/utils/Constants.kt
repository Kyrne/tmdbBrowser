package com.m7019e.tmdbbrowser.utils

import com.m7019e.tmdbbrowser.BuildConfig

object Constants {
    const val TMDB_MOVIE_GENRE_URL = "https://api.themoviedb.org/3/genre/movie/list"
    const val TMDB_API_MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val POSTER_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val BACKDROP_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val BACKDROP_IMAGE_WIDTH = "original"
    const val POSTER_IMAGE_WIDTH = "original"
    const val IMDB_BASE_URL = "https://www.imdb.com/title/"
    const val YOUTUBE_VIDEO_BASE_URL = "https://www.youtube.com/watch?v="
    const val YOUTUBE_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/"
    const val YOUTUBE_THUMBNAIL_QUALITY = "/maxresdefault.jpg"
    const val API_KEY = BuildConfig.API_KEY
}