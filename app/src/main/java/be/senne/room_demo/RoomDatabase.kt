package be.senne.room_demo

import androidx.room.Database

@Database(entities = [Entity::class], version = 1)
abstract class RoomDatabase {
    abstract fun entityDAO() : EntityDAO
}