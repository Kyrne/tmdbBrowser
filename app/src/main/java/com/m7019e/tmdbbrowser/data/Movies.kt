@file:Suppress("SpellCheckingInspection")

package com.m7019e.tmdbbrowser.data

import androidx.compose.ui.layout.ScaleFactor
import com.m7019e.tmdbbrowser.model.Genre
import com.m7019e.tmdbbrowser.model.Movie

object Movies {
    val defaultMovie = getMovies()[0]

    fun getMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 823464,
                title = "Godzilla x Kong: The New Empire",
                posterPath = "/tMefBSflR6PGQLv7WvFPpKLZkyk.jpg",
                backdropPath = "/sR0SpCrXamlIkYMdfz83sFn5JS6.jpg",
                releaseDate = "2024-03-27",
                overview = "Following their explosive showdown, Godzilla and Kong must reunite against a colossal undiscovered threat hidden within our world, challenging their very existence – and our own.",
                genres = listOf(
                    Genre.ACTION,
                    Genre.SCIENCE_FICTION,
                    Genre.ADVENTURE,
                    Genre.FANTASY
                ),
                homepage = "https://www.godzillaxkongmovie.com"
            ),
            Movie(
                id = 1011985,
                title = "Kung Fu Panda 4",
                posterPath = "/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg",
                backdropPath = "/1XDDXPXGiI8id7MrUxK36ke7gkX.jpg",
                releaseDate = "2024-03-02",
                overview = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.",
                genres = listOf(
                    Genre.ANIMATION,
                    Genre.ACTION,
                    Genre.ADVENTURE,
                    Genre.COMEDY,
                    Genre.FAMILY
                ),
                homepage = "https://www.dreamworks.com/movies/kung-fu-panda-4"
            ),
            Movie(
                id = 359410,
                title = "Road House",
                posterPath = "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                backdropPath = "/oe7mWkvYhK4PLRNAVSvonzyUXNy.jpg",
                releaseDate = "2024-03-08",
                overview = "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys roadhouse, only to discover that this paradise is not all it seems."
                , genres = listOf(Genre.ACTION,Genre.THRILLER),
                homepage = "https://www.amazon.com/gp/video/detail/B0CH5YQPZQ"
            ),
            Movie(
                id = 634492,
                title = "Madame Web",
                posterPath = "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg",
                backdropPath = "/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg",
                releaseDate = "2024-02-14",
                overview = "Forced to confront revelations about her past, paramedic Cassandra Webb forges a relationship with three young women destined for powerful futures...if they can all survive a deadly present."
                , genres = listOf(Genre.ACTION,Genre.FANTASY),
                homepage = "https://www.madameweb.movie"
            ),
            Movie(
                id = 935271,
                title = "After the Pandemic",
                posterPath = "/p1LbrdJ53dGfEhRopG71akfzOVu.jpg",
                backdropPath = "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                releaseDate = "2024-03-01",
                overview = "Set in a post-apocalyptic world where a global airborne pandemic has wiped out 90% of the Earth's population and only the young and immune have endured as scavengers. For Ellie and Quinn, the daily challenges to stay alive are compounded when they become hunted by the merciless Stalkers."
                , genres = listOf(Genre.SCIENCE_FICTION,Genre.ACTION),
            ),
        )
    }
}