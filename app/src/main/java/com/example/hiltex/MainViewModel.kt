package com.example.hiltex

import androidx.lifecycle.*
import com.example.roomex.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MemoRepository ,
  ) : ViewModel() {

    val todos: LiveData<List<Todo>>
    val title: LiveData<List<String>>

    var newTodo: String? = null

    init {
        todos = getAll()
        title = observeTitle()
    }


    fun getAll(): LiveData<List<Todo>> {
        return repository.getAll()
    }

    fun observeTitle(): LiveData<List<String>> {
        return repository.observeTitle()
    }

    fun insert(todo: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(Todo(todo))
        }
    }

    fun nukeTable() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.nukeTable()
        }
    }
}
