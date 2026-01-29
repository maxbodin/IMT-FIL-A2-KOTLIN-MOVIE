package movie.ui.home

import movie.domain.model.Movie

data class HomeState(
    val searchText: String? = null,
    val isLoading: Boolean? = false,
    val movies: List<Movie> = emptyList()
)