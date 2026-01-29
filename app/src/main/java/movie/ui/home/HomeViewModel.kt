package movie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import movie.data.repository.MovieRepositoryImpl
import movie.domain.repository.MovieRepository

class HomeViewModel(
    private val movieRepository: MovieRepository = MovieRepositoryImpl()
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    init {
        println("HomeViewModel initialized")
    }

    fun onEvent(event: HomeUIEvent) {
        when (event) {
            is HomeUIEvent.ChangeSearchText -> updateSearchText(event.text)
            is HomeUIEvent.ClearSearchText -> clearSearchText()
            is HomeUIEvent.SubmitSearch -> searchMovie()
        }
    }

    private fun updateSearchText(searchText: String) {
        _state.update {
            it.copy(searchText = searchText)
        }
    }

    private fun clearSearchText() {
        _state.update {
            it.copy(searchText = null)
        }
    }


    private fun searchMovie() {
        viewModelScope.launch {
            _state.value.searchText?.let {
                _state.update { state ->
                    state.copy(isLoading = true)
                }
                movieRepository.findMovie(name = it).collect { result ->
                    result.fold(
                        onSuccess = { movies ->
                            _state.update { state ->
                                state.copy(movies = movies, isLoading = false)
                            }
                        },
                        onFailure = { error ->
                            _state.update { state ->
                                state.copy(movies = emptyList(), isLoading = false)
                            }
                        }
                    )
                }
            }
        }
    }
}

sealed interface HomeUIEvent {
    data class ChangeSearchText(val text: String) : HomeUIEvent
    data object ClearSearchText : HomeUIEvent
    data object SubmitSearch : HomeUIEvent
}
