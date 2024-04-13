package com.m7019e.tmdbbrowser.ui.screens

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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.utils.Constants


@Composable
fun MovieListScreen(
    movieList: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(movieList) { movie ->
            MovieListCard(movie = movie, onClick = onMovieClick, modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListCard(movie: Movie, onClick: (Movie) -> Unit, modifier: Modifier = Modifier) {
    Card(onClick = { onClick(movie) }, modifier = modifier.fillMaxWidth()) {
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
            Column(modifier = Modifier.padding(2.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
                Text(text = movie.releaseDate, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
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