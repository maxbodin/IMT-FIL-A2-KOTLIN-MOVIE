package movie.data.network

import movie.data.network.dto.response.MovieResponse
import movie.domain.model.Movie

fun MovieResponse.toDomain(): List<Movie> {
    return this.description.map {
        Movie(
            id = it.imdbId,
            title = it.title,
            year = it.year,
            actors = it.actors ?: "N/A",
            posterUrl = it.imgPoster,
            rank = it.rank
        )
    }
}