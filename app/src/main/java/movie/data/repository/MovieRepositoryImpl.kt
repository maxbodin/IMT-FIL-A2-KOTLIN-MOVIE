package movie.data.repository

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import movie.data.network.ApiClient
import movie.data.network.ApiService
import movie.data.network.toDomain
import movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiService: ApiService = ApiClient.create()
) : MovieRepository {

    override suspend fun findMovie(name: String) = flow {
        val response = apiService.searchMovie(
            search = name
        )
        emit(Result.success(response.toDomain()))
    }.catch { throwable ->
        emit(Result.failure(throwable))
    }
}