package com.m7019e.tmdbbrowser.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.m7019e.tmdbbrowser.model.Movie

@Composable
fun MovieCreateReviewScreen(movie: Movie) {
    Text(text = "Placeholder for creating reviews. Current movie selected %s".format(movie.title))
}