package com.m7019e.tmdbbrowser.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.m7019e.tmdbbrowser.TMDBApplication
import com.m7019e.tmdbbrowser.data.MoviesRepository
import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.model.Review
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.IOException


sealed interface MovieListUiState {
    data class Success(val movies: List<Movie>) : MovieListUiState
    object Error : MovieListUiState
    object Loading : MovieListUiState
}

sealed interface SelectedMovieUiState {
    data class Success(val movie: Movie) : SelectedMovieUiState
    object Error : SelectedMovieUiState
    object Loading : SelectedMovieUiState
}

sealed interface ReviewUiState {
    data class Success(val reviews: List<Review>) : ReviewUiState
    object Error : ReviewUiState
    object Loading : ReviewUiState
}


enum class Layout {
    GRID, LIST
}

class MovieViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var movieListUiState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set

    var selectedMovieUiState: SelectedMovieUiState by mutableStateOf(SelectedMovieUiState.Loading)
        private set

    var movieUiLayout: Layout by mutableStateOf(Layout.GRID)
        private set

    var reviewUiState: ReviewUiState by mutableStateOf(ReviewUiState.Loading)
        private set

    var genreMap: Map<Int, String> = mapOf()
        private set

    init {
        runBlocking { getGenreList() }
        getPopularMovies()
    }

    private suspend fun getGenreList() {
        genreMap = try {
            moviesRepository.getGenreList()
        } catch (e: IOException) {
            mapOf()
        } catch (e: HttpException) {
            mapOf()
        }

    }

    fun getPopularMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                val movieList = moviesRepository.getPopularMovies().results
                for (movie in movieList) {
                    movie.setGenreList(genreMap)
                }
                MovieListUiState.Success(movieList)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(moviesRepository.getTopRatedMovies().results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun setSelectedMovie(movie: Movie) {
        viewModelScope.launch {
            selectedMovieUiState = SelectedMovieUiState.Loading
            selectedMovieUiState = try {
                val details: Movie = moviesRepository.getMovieDetails(movie)
                movie.updateDetails(details)
                SelectedMovieUiState.Success(movie)
            } catch (e: IOException) {
                SelectedMovieUiState.Error
            } catch (e: HttpException) {
                SelectedMovieUiState.Error
            }
        }
    }

    fun changeLayout() {
        movieUiLayout = if (movieUiLayout == Layout.GRID) {
            Layout.LIST
        } else {
            Layout.GRID
        }
    }

    fun getMovieReviews(movie: Movie) {
        viewModelScope.launch {
            reviewUiState = ReviewUiState.Loading
            reviewUiState = try {
                ReviewUiState.Success(moviesRepository.getMovieReviews(movie).reviews)
            } catch (e: IOException) {
                ReviewUiState.Error
            } catch (e: HttpException) {
                ReviewUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TMDBApplication)
                val moviesRepository = application.container.moviesRepository
                MovieViewModel(moviesRepository = moviesRepository)
            }
        }
    }

}
