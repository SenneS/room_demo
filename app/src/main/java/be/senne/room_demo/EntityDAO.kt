package be.senne.room_demo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.Deferred

@Dao
interface EntityDAO {
    @Query("SELECT * FROM entity")
    suspend fun getAll() : List<Entity>

    @Query("SELECT * FROM entity WHERE id = :id")
    suspend fun getById(id : Int) : Entity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: Entity)

    @Delete()
    suspend fun delete(entity: Entity)
    @Update()
    suspend fun update(entity: Entity)
}