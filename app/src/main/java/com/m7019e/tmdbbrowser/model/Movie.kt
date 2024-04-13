package com.m7019e.tmdbbrowser.model

import androidx.annotation.StringRes
import com.m7019e.tmdbbrowser.R


/**
 * A class representing a *movie* and it's details
 *
 * @param id movie id on TMDB
 * @param title movie title
 * @param posterPath path to poster used in the movie list
 * @param backdropPath path to backdrop used in detail page
 * @param releaseDate release date of the movie in YYYY-MM-DD
 * @param overview a short description of the movie
 */
data class Movie(
    var id: Int,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var overview: String,
    var genres: List<Genre>,
    var homepage: String? = null
)

enum class Genre(@StringRes val genre_name: Int) {
    ACTION(genre_name = R.string.genre_action),
    ADVENTURE(genre_name = R.string.genre_adventure),
    ANIMATION(genre_name = R.string.genre_animation),
    COMEDY(genre_name = R.string.genre_comedy),
    FAMILY(genre_name = R.string.genre_family),
    FANTASY(genre_name = R.string.genre_fantasy),
    SCIENCE_FICTION(genre_name = R.string.genre_science_fiction),
    THRILLER(genre_name = R.string.genre_thriller)
}