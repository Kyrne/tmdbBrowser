package com.m7019e.tmdbbrowser.ui.screens.movie

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun MovieDetailsScreen(
    movie: Movie,
    onUserRatingClick: (Movie) -> Unit,
    onReviewClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = Constants.BACKDROP_IMAGE_BASE_URL + Constants.BACKDROP_IMAGE_WIDTH + movie.backdropPath,
                contentDescription = movie.title,
                contentScale = ContentScale.Fit
            )
        }
        Box(modifier = Modifier.padding(start = 4.dp, end = 4.dp)) {
            MovieDetails(movie, onUserRatingClick, onReviewClick)
        }

    }
}

@Composable
fun MovieDetails(
    movie: Movie, onUserRatingClick: (Movie) -> Unit,
    onCreateReviewClick: (Movie) -> Unit, modifier: Modifier = Modifier
) {
    Column {
        MovieDetailsTitle(movie = movie)
        MovieDetailsGenreList(movie = movie)
        MovieDetailsUserRating(movie = movie, onUserRatingClick, onCreateReviewClick)
        MovieDetailsOverview(movie = movie)
        Row {
            if (movie.homepage != null) {
                MovieDetailsLinkToHomepage(
                    movie = movie,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
            MovieDetailsIMDBLink(
                movie = movie,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }


    }
}

@Composable
fun MovieDetailsUserRating(
    movie: Movie, onUserRatingClick: (Movie) -> Unit,
    onCreateReviewClick: (Movie) -> Unit,
) {
    Row {
        Button(
            onClick = { onUserRatingClick(movie) },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier.weight(2f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(id = R.string.icon_star)
                )
                Text(text = "%.2f/10".format(movie.userRating))
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onCreateReviewClick(movie) },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.weight(2f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.TwoTone.Star,
                    contentDescription = stringResource(id = R.string.icon_star)
                )
                Text(text = stringResource(id = R.string.review_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsUserRatingPreview() {
    MovieDetailsUserRating(movie = Movies.defaultMovie, {}, {})
}

@Composable
fun MovieDetailsIMDBLink(movie: Movie, modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    Button(
        onClick = {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(Constants.IMDB_BASE_URL + movie.imdbId))
            ctx.startActivity(browserIntent)
        },
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ), modifier = modifier
    ) {
        Icon(
            imageVector = Icons.TwoTone.ExitToApp,
            contentDescription = stringResource(id = R.string.movie_imdb_button),
        )
        Text(
            text = stringResource(id = R.string.movie_imdb_button),
        )
    }
}

@Composable
fun MovieDetailsLinkToHomepage(movie: Movie, modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    Button(
        onClick = {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.homepage))
            ctx.startActivity(browserIntent)
        },
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ), modifier = modifier

    ) {
        Icon(
            imageVector = Icons.TwoTone.ExitToApp,
            contentDescription = stringResource(id = R.string.movie_homepage_button),
        )
        Text(
            text = stringResource(id = R.string.movie_homepage_button),
        )
    }
}

@Composable
fun MovieDetailsOverview(movie: Movie) {
    Column(modifier = Modifier.fillMaxWidth()) {
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
    MovieDetails(Movies.getMovies()[0], {}, {})
}
