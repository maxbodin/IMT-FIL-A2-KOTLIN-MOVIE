package movie.domain.model

data class Movie(
    val id: String,
    val title: String,
    val year: Int,
    val actors: String,
    val posterUrl: String?,
    val rank: Int
)