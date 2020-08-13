package com.begoml.datasource_impl.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase

@Database(
    entities = [
        TestTemp::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
}

@Entity
data class TestTemp(

    @PrimaryKey
    val id: Int
)