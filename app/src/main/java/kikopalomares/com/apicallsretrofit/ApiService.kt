package kikopalomares.com.apicallsretrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Project project
 * Created by Francisco Palomares Barrios on 07/06/2018.
 */
interface ApiService {

    @GET("posts/")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Call<Post>

    @POST("posts/{id}")
    fun editPostById(@Path("id") id: Int, @Body post: Post?): Call<Post>
}