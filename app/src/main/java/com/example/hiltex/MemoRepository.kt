package com.example.hiltex

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roomex.Todo
import javax.inject.Inject
import javax.inject.Singleton

class MemoRepository @Inject constructor(private val db : MemoDatabase){

    fun getAll() : LiveData<List<Todo>>{
       return db.todoDao().getAll()
    }

    fun observeTitle() : LiveData<List<String>> {
        return db.todoDao().observeTitle()
    }

    fun insert(todo: Todo){
        db.todoDao().insert(todo)
    }

    fun delete(todo: Todo){
        db.todoDao().delete(todo)
    }

    fun update(todo: Todo){
        db.todoDao().update(todo)
    }

    fun nukeTable(){
        db.todoDao().nukeTable()
    }


}
