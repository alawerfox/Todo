package com.todoList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoList.db.Entity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
): ViewModel() {
    private val _status = MutableLiveData<Flow<List<Entity>>>()
    val status: LiveData<Flow<List<Entity>>> = _status

    fun getAll() {
        viewModelScope.launch {
            try {
                _status.value = repository.updateTodo()
            } catch (e: Exception) {
            }
        }
    }

    fun addTodo(){
        viewModelScope.launch {
            try {
                _status.value = repository.addTodo()
            } catch (e: Exception) {
            }
        }
    }

    fun deleteTodo(){
        viewModelScope.launch {
            try {
                _status.value = repository.deleteTodo()
            } catch (e: Exception) {
            }
        }
    }

}