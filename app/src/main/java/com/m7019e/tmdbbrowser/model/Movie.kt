package com.m7019e.tmdbbrowser.model

import androidx.annotation.StringRes
import com.m7019e.tmdbbrowser.R


/**
 * A class representing a *movie* and it's details
 *
 * @param tmdbId movie id on TMDB
 * @param imdbId movie id on IMDB
 * @param title movie title
 * @param posterPath path to poster used in the movie list
 * @param backdropPath path to backdrop used in detail page
 * @param releaseDate release date of the movie in YYYY-MM-DD
 * @param overview a short description of the movie
 */
data class Movie(
    var tmdbId: Int,
    var imdbId: String,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var overview: String,
    var userRating: Float,
    var genres: List<Genre>,
    var homepage: String? = null
)

enum class Genre(@StringRes val genre: Int) {
    ACTION(genre = R.string.genre_action),
    ADVENTURE(genre = R.string.genre_adventure),
    ANIMATION(genre = R.string.genre_animation),
    COMEDY(genre = R.string.genre_comedy),
    FAMILY(genre = R.string.genre_family),
    FANTASY(genre = R.string.genre_fantasy),
    SCIENCE_FICTION(genre = R.string.genre_science_fiction),
    THRILLER(genre = R.string.genre_thriller)
}