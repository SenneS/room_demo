package be.senne.room_demo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Entity (
    //id mag null zijn door de autogenerate
    @PrimaryKey(autoGenerate = true) val id : Int?,
    val title : String,
    val description : String
)
