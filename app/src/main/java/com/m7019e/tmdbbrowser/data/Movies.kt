@file:Suppress("SpellCheckingInspection")

package com.m7019e.tmdbbrowser.data

import com.m7019e.tmdbbrowser.model.Movie

object Movies {
    val defaultMovie = getMovies()[0]

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                imdbId = "tt14539740",
                title = "Godzilla x Kong: The New Empire",
                posterPath = "/tMefBSflR6PGQLv7WvFPpKLZkyk.jpg",
                backdropPath = "/sR0SpCrXamlIkYMdfz83sFn5JS6.jpg",
                releaseDate = "2024-03-27",
                overview = "Following their explosive showdown, Godzilla and Kong must reunite against a colossal undiscovered threat hidden within our world, challenging their very existence – and our own.",
                userRating = 6.74f,
                genre_ids = listOf(),
                genreList = listOf(
                    "Action",
                    "Science Fiction",
                    "Adventure",
                    "Fantasy"
                ),
                homepage = "https://www.godzillaxkongmovie.com",
            ),
        )
    }
}