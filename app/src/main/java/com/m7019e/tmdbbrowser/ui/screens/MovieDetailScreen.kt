package com.m7019e.tmdbbrowser.ui.screens.movie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.m7019e.tmdbbrowser.R
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.utils.Constants

@Composable
fun MovieDetailsScreen(movie: Movie, modifier: Modifier = Modifier) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = Constants.BACKDROP_IMAGE_BASE_URL + Constants.BACKDROP_IMAGE_WIDTH + movie.backdropPath,
                contentDescription = movie.title,
                contentScale = ContentScale.Fit
            )
        }
        Box(modifier = Modifier.padding(start = 4.dp, end = 4.dp)) {
            MovieDetails(movie)
        }

    }
}

@Composable
fun MovieDetailsHyperlink(movie: Movie) {

}

@Composable
fun MovieDetails(movie: Movie, modifier: Modifier = Modifier) {
    Column {
        MovieDetailsTitle(movie = movie)
        MovieDetailsGenreList(movie = movie)
        Spacer(modifier = Modifier.size(8.dp))
        MovieDetailsOverview(movie = movie)
    }
}

@Composable
fun MovieDetailsOverview(movie: Movie) {
    Text(
        text = stringResource(id = R.string.overview),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
    Text(
        text = movie.overview,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsTitle(movie: Movie) {
    FlowRow(Modifier.fillMaxWidth()) {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = "(" + movie.releaseDate + ")",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MovieDetailsGenreList(movie: Movie) {
    FlowRow(Modifier.fillMaxWidth()) {
        repeat(movie.genres.size) {
            Text(
                text = stringResource(id = movie.genres[it].genre),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieDetails(Movies.getMovies()[0])
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview2() {
    MovieDetails(Movies.getMovies()[1])
}