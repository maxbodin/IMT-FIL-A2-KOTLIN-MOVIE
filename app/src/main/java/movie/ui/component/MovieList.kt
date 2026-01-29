package movie.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import movie.domain.model.Movie
import movie.ui.home.HomeState

@Composable
fun MovieList(
    state: HomeState,
    movies: List<Movie>,
    onMovieClicked: (movie: Movie) -> Unit
) {
    if (state.isLoading == true) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies, key = { it.id }) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = {
                        onMovieClicked(it)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovieList() {
    val sampleMovies = listOf(
        Movie("1", "Inception", 2010, "Leonardo DiCaprio, Joseph Gordon-Levitt", null, 2),
        Movie("2", "The Dark Knight", 2008, "Christian Bale, Heath Ledger", null, 5)
    )
    MovieList(
        state = HomeState(movies = sampleMovies, isLoading = false, searchText = "Batman"),
        onMovieClicked = {},
        movies = sampleMovies,
    )
}