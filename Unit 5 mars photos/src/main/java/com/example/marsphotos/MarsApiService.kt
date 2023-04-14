import retrofit2.Retrofit
import retrofit2.http.GET
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService{
    @Get("photos")
    suspend fun getPhotos(): List<MarsPhoto>{

    }
}

object MarsApi{
    val retrofitService: MarsAPiService by lazy{
        retrofit.create(MarsApiService::class.java)
    }
}