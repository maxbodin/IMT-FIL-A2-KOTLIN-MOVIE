package movie.domain.repository

import kotlinx.coroutines.flow.Flow
import movie.domain.model.Movie

interface MovieRepository {

    suspend fun findMovie(name: String): Flow<Result<List<Movie>>>
}