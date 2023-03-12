package com.todoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoList.db.Entity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
): ViewModel() {

    val list = MutableLiveData<List<Entity>>()

    fun getAll() {
        viewModelScope.launch {
            repository.findAll()
                .onEach { list.value = it }.collect()
        }
    }

    fun deleteTodo(todoList: Entity){
        viewModelScope.launch { repository.deleteTodo(todoList)  }
    }
}