package com.example.hiltex.data

import androidx.room.Database
import androidx.room.RoomDatabase
// 메모DB 별도로 싱글톤화를 안시켜줘도
// hilt를 통해 싱글톤화 시킨다
@Database(entities = [Todo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao


}