package be.senne.room_demo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityDAO {
    @Query("SELECT * FROM entity")
    fun getAll() : List<Entity>

    @Query("SELECT * FROM entity WHERE id = :id")
    fun getById(id : Int) : Entity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: Entity)

    @Delete()
    fun delete(entity: Entity)
    @Update()
    fun update(entity: Entity)
}