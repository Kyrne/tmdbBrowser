package com.m7019e.tmdbbrowser.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.m7019e.tmdbbrowser.R
import com.m7019e.tmdbbrowser.data.Movies
import com.m7019e.tmdbbrowser.ui.screens.MovieCreateReviewScreen
import com.m7019e.tmdbbrowser.ui.screens.MovieListScreen
import com.m7019e.tmdbbrowser.ui.screens.MovieUserRatingsScreen
import com.m7019e.tmdbbrowser.ui.screens.movie.MovieDetailsScreen

enum class TmdbBrowserScreen(@StringRes val title: Int) {
    List(title = R.string.app_name),
    Details(title = R.string.movie_details),
    UserRatings(title = R.string.user_ratings),
    CreateReview(title = R.string.create_review)
}

@Composable
fun TmdbBrowserApp() {
    val viewModel: MovieViewModel = viewModel()
    val navController: NavHostController = rememberNavController()
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        TmdbBrowserScreen.valueOf(backStackEntry?.destination?.route ?: TmdbBrowserScreen.List.name)

    Scaffold(topBar = {
        TmdbBrowserAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() })
    }) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = TmdbBrowserScreen.List.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = TmdbBrowserScreen.List.name) {
                MovieListScreen(
                    movieList = Movies.getMovies(),
                    onMovieClick = { movie ->
                        viewModel.updateCurrentMovie(movie)
                        navController.navigate(TmdbBrowserScreen.Details.name)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = TmdbBrowserScreen.Details.name) {
                uiState.currentMovie?.let { movie ->
                    MovieDetailsScreen(
                        movie = movie,
                        onUserRatingClick = {
                            viewModel.updateCurrentMovie(it) // Might be redundant
                            navController.navigate(TmdbBrowserScreen.UserRatings.name)
                        },
                        onReviewClick = {
                            viewModel.updateCurrentMovie(it) // Might be redundant
                            navController.navigate(TmdbBrowserScreen.CreateReview.name)
                        })
                }
            }
            composable(route = TmdbBrowserScreen.UserRatings.name) {
                uiState.currentMovie?.let { movie ->
                    MovieUserRatingsScreen(movie = movie)
                }
            }
            composable(route = TmdbBrowserScreen.CreateReview.name) {
                uiState.currentMovie?.let { movie ->
                    MovieCreateReviewScreen(movie = movie)

                }
            }
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TmdbBrowserAppBar(
    currentScreen: TmdbBrowserScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_button
                        )
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TmdbBrowserAppPreview() {
    TmdbBrowserApp()
}
