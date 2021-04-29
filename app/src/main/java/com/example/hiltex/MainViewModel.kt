package com.example.hiltex

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.hiltex.repository.MemoRepository
import com.example.hiltex.data.Todo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MemoRepository,
) : ViewModel() {

    private val _items: LiveData<List<Todo>> = getAll()
    val items: LiveData<List<Todo>> = _items

    private val _showErrorToast = MutableLiveData<Event<Boolean>>()
    val showErrorToast: LiveData<Event<Boolean>> = _showErrorToast

    val title: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private fun getAll(): LiveData<List<Todo>> {
        return repository.getAll()
    }

    fun insert() {
        val currentTitle = title.value
        if (!currentTitle.isNullOrBlank())
            viewModelScope.launch {
                repository.insert(Todo(currentTitle))
            }
        else {
            showToast()
        }
        title.value=""
    }

    fun nukeTable() {
        viewModelScope.launch {
            repository.nukeTable()
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }

    fun showToast() {
        _showErrorToast.value = Event(true)
    }

}

