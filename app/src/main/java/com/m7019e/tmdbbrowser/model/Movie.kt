package com.m7019e.tmdbbrowser.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


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
@Serializable
data class Movie(
    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName(value = "title")
    var title: String = "",

    @SerialName(value = "poster_path")
    var posterPath: String = "",

    @SerialName(value = "backdrop_path")
    var backdropPath: String = "",

    @SerialName(value = "release_date")
    var releaseDate: String = "",

    @SerialName(value = "overview")
    var overview: String = "",

    @SerialName(value = "vote_average")
    var userRating: Float = 0f,

    @SerialName(value = "genre_ids")
    var genre_ids: List<Int> = listOf(),

    var genreList: List<String> = listOf(),

    @SerialName(value = "homepage")
    var homepage: String = "",

    @SerialName(value = "imdb_id")
    var imdbId: String = "",


    ) {
    fun updateDetails(details: Movie) {
        if (details.homepage.isNotEmpty()) {
            this.homepage = details.homepage
        }
        if (details.imdbId.isNotEmpty()) {
            this.imdbId = details.imdbId
        }
    }

    fun setGenreList(genreMap: Map<Int, String>) {
        val genreList: MutableList<String> = mutableListOf()
        for (genreId in this.genre_ids) {
            val genre = genreMap[genreId]
            if (genre != null) {
                genreList += genre
            }
        }
        this.genreList = genreList
    }
}

