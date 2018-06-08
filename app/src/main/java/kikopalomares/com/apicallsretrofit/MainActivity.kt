package kikopalomares.com.apicallsretrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val TAG_LOGS = "kikopalomares"

    lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create<ApiService>(ApiService::class.java)

        getAllPosts()
        getPostById()
        editPost()
    }

    fun getAllPosts(){
        //Recibimos todos los posts
        service.getAllPosts().enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                val posts = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(posts))
            }
            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun getPostById(){
        //Recibimos los datos del post con ID = 1
        var post: Post? = null
        service.getPostById(1).enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                post = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(post))
            }
            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }

    fun editPost(){
        var post: Post? = Post(1, 1, "Hello k", "body")
        //Editamos los datos por POST
        service.editPostById(1, post).enqueue(object: Callback<Post>{
            override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                post = response?.body()
                Log.i(TAG_LOGS, Gson().toJson(post))
            }
            override fun onFailure(call: Call<Post>?, t: Throwable?) {
                t?.printStackTrace()
            }
        })
    }
}
