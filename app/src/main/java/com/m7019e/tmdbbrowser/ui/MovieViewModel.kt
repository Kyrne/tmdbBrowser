package com.m7019e.tmdbbrowser.ui

import androidx.lifecycle.ViewModel
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MovieUiState(
        currentMovie = Movies.getMovies().getOrElse(0){
            Movies.defaultMovie
        }
    ))

    val uiState: StateFlow<MovieUiState> = _uiState

    fun updateCurrentMovie(selectedMovie: Movie){
        _uiState.update {
            it.copy(currentMovie = selectedMovie)
        }
    }
}

data class MovieUiState(
    val currentMovie: Movie? = null
)