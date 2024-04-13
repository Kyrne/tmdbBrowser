package com.m7019e.tmdbbrowser.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.ui.screens.MovieListScreen

@Composable
fun TmdbBrowserApp() {
    MovieListScreen(movieList = Movies.getMovies())
}

@Preview(showBackground = true)
@Composable
fun TmdbBrowserAppPreview() {
    TmdbBrowserApp()
}
