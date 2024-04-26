package com.m7019e.tmdbbrowser.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
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
@Entity(tableName = "favorite_movies")
data class Movie(
    @PrimaryKey
    @SerialName(value = "id")
    var id: Long = 0,

    @SerialName(value = "title")
    var title: String,

    @SerialName(value = "poster_path")
    var posterPath: String,

    @SerialName(value = "backdrop_path")
    var backdropPath: String,

    @SerialName(value = "release_date")
    var releaseDate: String,

    @SerialName(value = "overview")
    var overview: String,

    @SerialName(value = "vote_average")
    var userRating: Float,

    @SerialName(value = "genre_ids")
    var genre_ids: List<Int> = listOf(),

    var genreList: List<String> = listOf(),

    @SerialName(value = "homepage")
    var homepage: String = "",

    @SerialName(value = "imdb_id")
    var imdbId: String = "",

    var videoList: List<Video> = listOf()
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

class Converters {
    @TypeConverter
    fun fromListInt(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toListInt(data: String): List<Int> {
        return listOf(*data.split(",").map { it.toInt() }.toTypedArray())
    }

    @TypeConverter
    fun fromListString(list: List<String>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun toListString(data: String): List<String> {
        return listOf(*data.split(",").map { it }.toTypedArray())
    }

    @TypeConverter
    fun fromListVideo(list: List<Video>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toListVideo(data: String): List<Video> {
        return Gson().fromJson(data, Array<Video>::class.java).asList()
    }

}
