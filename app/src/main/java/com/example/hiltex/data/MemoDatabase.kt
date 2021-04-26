package com.example.hiltex.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

}