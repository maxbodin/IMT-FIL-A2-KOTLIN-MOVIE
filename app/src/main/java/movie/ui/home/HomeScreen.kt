package movie.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import movie.domain.model.Movie
import movie.ui.component.MovieList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState,
    onEvent: (HomeUIEvent) -> Unit,
    onMovieClicked: (movie: Movie) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Recherche de Films") })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))

            // Section de recherche
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.searchText ?: "",
                onValueChange = { onEvent(HomeUIEvent.ChangeSearchText(it)) },
                label = { Text("Nom du film") },
                singleLine = true,
                trailingIcon = {
                    if (!state.searchText.isNullOrBlank()) {
                        IconButton(onClick = { onEvent(HomeUIEvent.ClearSearchText) }) {
                            Icon(Icons.Default.Clear, contentDescription = "Vider la recherche")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onEvent(HomeUIEvent.SubmitSearch) },
                enabled = !state.searchText.isNullOrBlank()
            ) {
                Icon(Icons.Default.Search, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Rechercher")
            }
            Spacer(modifier = Modifier.height(24.dp))

            MovieList(
                state = state,
                movies = state.movies,
                onMovieClicked = onMovieClicked
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val sampleMovies = listOf(
        Movie("1", "Inception", 2010, "Leonardo DiCaprio, Joseph Gordon-Levitt", null, 2),
        Movie("2", "The Dark Knight", 2008, "Christian Bale, Heath Ledger", null, 5)
    )
    HomeScreen(
        state = HomeState(movies = sampleMovies, isLoading = false, searchText = "Batman"),
        onEvent = {},
        onMovieClicked = {},
    )
}