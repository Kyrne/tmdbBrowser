package com.m7019e.tmdbbrowser.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.m7019e.tmdbbrowser.model.Movie


@Composable
fun MovieUserRatingsScreen(movie: Movie) {
    Text(text = "Placeholder for viewing user reviews. Current movie selected %s".format(movie.title))
}

