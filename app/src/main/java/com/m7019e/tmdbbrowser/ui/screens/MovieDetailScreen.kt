package com.m7019e.tmdbbrowser.ui.screens.movie

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.twotone.ExitToApp
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.m7019e.tmdbbrowser.R
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.model.Movie
import com.m7019e.tmdbbrowser.model.Video
import com.m7019e.tmdbbrowser.ui.SelectedMovieUiState
import com.m7019e.tmdbbrowser.utils.Constants

@Composable
fun MovieDetailsScreen(
    selectedMovieUiState: SelectedMovieUiState,
    isFavorite: Boolean,
    videoPlayer: Boolean,
    onFavoriteClick: (Movie) -> Unit,
    onUserRatingClick: (Movie) -> Unit,
    onReviewClick: (Movie) -> Unit,
    onVideoClick: (Video) -> Unit
) {
    when (selectedMovieUiState) {
        is SelectedMovieUiState.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = Constants.BACKDROP_IMAGE_BASE_URL + Constants.BACKDROP_IMAGE_WIDTH + selectedMovieUiState.movie.backdropPath,
                    contentDescription = selectedMovieUiState.movie.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                )
                MovieDetails(
                    selectedMovieUiState.movie,
                    isFavorite,
                    videoPlayer,
                    onFavoriteClick,
                    onUserRatingClick,
                    onReviewClick,
                    onVideoClick,
                    Modifier.padding(start = 4.dp, end = 4.dp),
                )

            }
        }

        is SelectedMovieUiState.Loading -> {
            Text(
                text = stringResource(id = R.string.api_loading_message),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        is SelectedMovieUiState.Error -> {
            Text(
                text = stringResource(id = R.string.api_error_message),
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}

@Composable
fun MovieDetails(
    movie: Movie,
    isFavorite: Boolean,
    videoPlayer: Boolean,
    onFavoriteClick: (Movie) -> Unit,
    onUserRatingClick: (Movie) -> Unit,
    onCreateReviewClick: (Movie) -> Unit,
    onVideoClick: (Video) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        MovieDetailsTitle(movie = movie)
        MovieDetailsGenreList(movie = movie)
        MovieDetailsUserRating(
            movie = movie,
            isFavorite,
            onFavoriteClick,
            onUserRatingClick,
            onCreateReviewClick
        )
        MovieDetailsOverview(movie = movie)
        Row {
            if (movie.homepage.isNotEmpty()) {
                MovieDetailsLinkToHomepage(
                    movie = movie,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
            if (movie.imdbId.isNotEmpty()) {
                MovieDetailsIMDBLink(
                    movie = movie,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                )
            }
        }
        if (movie.videoList.isNotEmpty()) {
            MovieVideosList(
                videos = movie.videoList,
                videoPlayer = videoPlayer,
                onVideoClick = onVideoClick
            )
        }


    }
}

@Composable
fun MovieDetailsUserRating(
    movie: Movie,
    isFavorite: Boolean,
    onFavoriteClick: (Movie) -> Unit,
    onUserRatingClick: (Movie) -> Unit,
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
        Button(
            onClick = { onFavoriteClick(movie) },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            modifier = Modifier.weight(2f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Filled.FavoriteBorder
                    },
                    contentDescription = stringResource(id = R.string.favorite_button)
                )
                Text(text = stringResource(id = R.string.favorite_button_label))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsUserRatingPreview() {
    MovieDetailsUserRating(movie = Movies.defaultMovie, isFavorite = false, {}, {}, {})
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

@Composable
fun MovieVideosList(videos: List<Video>, videoPlayer: Boolean, onVideoClick: (Video) -> Unit) {
    Text(
        text = stringResource(id = R.string.extras),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.SemiBold
    )
    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        items(videos) { video ->
            VideoCard(video = video, videoPlayer, onVideoClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoCard(video: Video, videoPlayer: Boolean, onVideoClick: (Video) -> Unit) {
    val ctx = LocalContext.current
    Column(modifier = Modifier.width(228.dp)) {
        Card(onClick = {
            if (video.site != "YouTube" || videoPlayer) {
                onVideoClick(video)
            } else {
                val browserIntent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(Constants.YOUTUBE_VIDEO_BASE_URL + video.key)
                    )
                ctx.startActivity(browserIntent)
            }
        }) {
            AsyncImage(
                model = Constants.YOUTUBE_THUMBNAIL_BASE_URL + video.key + Constants.YOUTUBE_THUMBNAIL_QUALITY,
                contentDescription = video.name,
                modifier = Modifier.height(128.dp),
                contentScale = ContentScale.Fit
            )
        }

        Box(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = video.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = video.type,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun VideoCardPreview() {
    VideoCard(video = Video(name = "Video title", type = "Trailer", key = "2", site = "Youtube"),
        videoPlayer = false, {})
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
        for (genre in movie.genreList) {
            Text(
                text = genre,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailsPreview() {
    MovieDetails(Movies.getMovies()[0], isFavorite = true, false, {}, {}, {}, {})
}

