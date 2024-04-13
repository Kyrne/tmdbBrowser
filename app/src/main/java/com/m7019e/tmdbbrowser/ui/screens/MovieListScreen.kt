package com.m7019e.tmdbbrowser.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.m7019e.tmdbbrowser.R
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.ui.screens.movie.MovieDetailsGenreList
import com.m7019e.tmdbbrowser.utils.Constants


@Composable
fun MovieListScreen(
    movieList: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(movieList) { movie ->
            MovieListCard(movie = movie, onClick = onMovieClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListCard(movie: Movie, onClick: (Movie) -> Unit, modifier: Modifier = Modifier) {
    OutlinedCard(
        onClick = { onClick(movie) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RectangleShape,
        border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier.fillMaxWidth()
    ) {
        Row {
            Box {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = modifier
                        .width(92.dp)
                        .height(138.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.padding(start = 8.dp))
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(text = movie.releaseDate, style = MaterialTheme.typography.titleSmall)
                MovieDetailsGenreList(movie = movie)
            }
        }
    }
}

@Preview
@Composable
fun MovieListPreview() {
    MovieListScreen(movieList = Movies.getMovies(), {})
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieListCard(movie = Movies.defaultMovie, {})
}