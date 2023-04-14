import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.network.MarsApi
import com.example.marsphotos.network.MarsApiService
interface MarsPhotosRepository{
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class DefaultMarsPhotosRepository: MarsPhotoRepository (
private val marsApiService: MarsApiService) : MarsPhotosRepository{
    override suspend fun getMarsPhotos(): List<MarsPhoto>{
        return MarsApi.retrofitService.getPhotos()
    }

    return marsApiService.getPhotos()
}