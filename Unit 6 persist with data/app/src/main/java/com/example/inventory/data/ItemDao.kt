import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item:Item)

    @Update
    suspend fun update(item:Item)

    @Delete
    suspend fun delete(item:Item)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int) : FLOW<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllITEMs(): Flow<List<Item>>
}