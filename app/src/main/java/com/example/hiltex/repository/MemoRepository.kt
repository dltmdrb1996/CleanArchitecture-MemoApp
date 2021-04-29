package com.example.hiltex.repository

import androidx.lifecycle.LiveData
import com.example.hiltex.data.MemoDatabase
import com.example.hiltex.data.Todo
import kotlinx.coroutines.*
import javax.inject.Inject

// 여러개의 data를 다루는 예제가 아니라 사실상 통로 역할정도를 하고있다.
class MemoRepository constructor(private val db: MemoDatabase) {

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    suspend fun insert(todo: Todo) {
        withContext(Dispatchers.IO) {
            db.todoDao().insert(todo)
        }
    }

    suspend fun delete(todo: Todo) {
        withContext(Dispatchers.IO) {
            db.todoDao().delete(todo)
        }
    }

    suspend fun nukeTable() {
        withContext(Dispatchers.IO) {
            db.todoDao().nukeTable()
        }
    }


}
