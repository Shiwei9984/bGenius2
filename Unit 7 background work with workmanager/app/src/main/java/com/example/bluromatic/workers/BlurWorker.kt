import androidx.work.CoroutineWorker
import androidx.work.WorkerParameter
import android.content.Context
import com.example.bluromatic.R
import com.example.bluromatic.KEY_BLUR_LEVEL
import com.example.bluromatic.KEY_IMAGE_URI
import android.net.Uri
import androidx.work.workDataOf

class BlurWorker(ctx : Context, params: WorkerParameter) : CoroutineWorker(ctx, params){
    override suspend fun doWork(): Result {
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        val blurLevel = inputData.getInt(KEY_BLUR_LEVEL,1)
        return withContext(Dispatchers.IO) {
            return @withContext try {
                require(!resourceUri.isNullOrBlank()){
                    val errorMessage =
                        applicationContext.resources.getString(R.string.invalid_input_uri)
                    Log.e(TAG,errorMessage)
                    errorMessage
                }
                val resolver = applicationContext.contentResolver
                delay(DELAY_TIME_MILLIS)
                val picture = BitmapFactory.decodeStream(
                    resolver.openInputStream(Uri.parse(resourceUri))
                )
                val output = blurBitmap(picture, blurLevel)
                val outputUri = writeBitmapToFile(applicationContext, output)
                makeStatusNotification(
                    "Output is $outputUri",
                    applicationContext
                )
                val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
                Result.success(outputData)
            } catch (throwable: Throwable) {
                Log.e(
                    TAG,
                    applicationContext.resources.getString(R.string.error_applying_blur),
                    throwable
                )
                Result.failure()
            }
        }
    }
}