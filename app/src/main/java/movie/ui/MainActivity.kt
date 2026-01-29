package movie.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import movie.ui.home.HomeScreen
import movie.ui.home.HomeViewModel
import movie.ui.movie_details.MovieDetailScreen
import movie.ui.theme.AppTheme

object Routes {
    const val HOME = "home"
    const val MOVIE_DETAILS_ROUTE = "movie_details"
    const val ARG_MOVIE_ID = "movieId"
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                val navController = rememberNavController()

                val homeViewModel: HomeViewModel = viewModel()

                Scaffold { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(route = Routes.HOME) {
                            val state by homeViewModel.state.collectAsStateWithLifecycle()

                            HomeScreen(
                                modifier = Modifier.padding(paddingValues),
                                onEvent = homeViewModel::onEvent,
                                state = state,
                                onMovieClicked = { movie ->
                                    navController.navigate("${Routes.MOVIE_DETAILS_ROUTE}/${movie.id}")
                                }
                            )
                        }
                        composable(
                            route = "${Routes.MOVIE_DETAILS_ROUTE}/{${Routes.ARG_MOVIE_ID}}",
                            arguments = listOf(navArgument(Routes.ARG_MOVIE_ID) {
                                type = NavType.StringType
                            })
                        ) { navBackStackEntry ->

                            val movieId =
                                navBackStackEntry.arguments?.getString(Routes.ARG_MOVIE_ID)
                            val movie =
                                homeViewModel.state.collectAsState().value.movies.find { it.id == movieId }

                            if (movie != null) {
                                MovieDetailScreen(
                                    movie = movie,
                                    onNavigateBack = {
                                        navController.popBackStack() // Go back to the previous screen
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}