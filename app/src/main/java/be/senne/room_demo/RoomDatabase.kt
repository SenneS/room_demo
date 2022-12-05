package be.senne.room_demo

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun entityDAO() : EntityDAO

    companion object {
        var instance = lazy {
            Room.databaseBuilder(MainActivity.context, be.senne.room_demo.RoomDatabase::class.java, "mijn-database").build()
        }
    }

}