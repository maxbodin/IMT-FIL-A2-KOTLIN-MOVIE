package movie.data.network.dto.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("ok")
    val ok: Boolean,

    @SerializedName("description")
    val description: List<MovieDescription>,

    @SerializedName("error_code")
    val errorCode: Int
)

data class MovieDescription(
    @SerializedName("#TITLE")
    val title: String,

    @SerializedName("#YEAR")
    val year: Int,

    @SerializedName("#IMDB_ID")
    val imdbId: String,

    @SerializedName("#RANK")
    val rank: Int,

    @SerializedName("#ACTORS")
    val actors: String? = null,

    @SerializedName("#AKA")
    val aka: String? = null,

    @SerializedName("#IMDB_URL")
    val imdbUrl: String? = null,

    @SerializedName("#IMDB_IV")
    val imdbIv: String? = null,

    @SerializedName("#IMG_POSTER")
    val imgPoster: String? = null,

    @SerializedName("photo_width")
    val photoWidth: Int? = null,

    @SerializedName("photo_height")
    val photoHeight: Int? = null
)