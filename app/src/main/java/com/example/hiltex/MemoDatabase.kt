package com.example.hiltex

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomex.Todo
import com.example.roomex.TodoDao
import javax.inject.Inject

@Database(entities = [Todo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

}