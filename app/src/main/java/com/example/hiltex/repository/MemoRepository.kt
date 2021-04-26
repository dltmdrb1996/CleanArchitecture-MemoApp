package com.example.hiltex.repository

import androidx.lifecycle.LiveData
import com.example.hiltex.data.MemoDatabase
import com.example.hiltex.data.Todo
import javax.inject.Inject

class MemoRepository constructor(private val db : MemoDatabase){

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
