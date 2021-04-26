package com.example.hiltex.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>

    @Query("SELECT title FROM Todo")
    fun observeTitle(): LiveData<List<String>>

    @Insert
    fun insert(todo: Todo)

    @Delete
    fun delete(todo: Todo)

    @Update
    fun update(todo: Todo)

    @Query("DELETE FROM Todo")
    fun nukeTable()
}