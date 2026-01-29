package movie.data.network

import movie.data.network.dto.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun searchMovie(@Query("q") search: String): MovieResponse
}