package com.m7019e.tmdbbrowser.model


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
    val id: Int,
    val title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var overview: String,
)