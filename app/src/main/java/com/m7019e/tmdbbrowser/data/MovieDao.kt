package com.m7019e.tmdbbrowser.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.m7019e.tmdbbrowser.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    suspend fun getFavoriteMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM favorite_movies WHERE id = :id")
    suspend fun getMovie(id: Long): Movie

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Long)

}