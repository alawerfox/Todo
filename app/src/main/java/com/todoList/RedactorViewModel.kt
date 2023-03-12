package com.todoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoList.db.Entity
import kotlinx.coroutines.launch

class RedactorViewModel(
    private val repository: Repository
): ViewModel() {

    fun update(todoList: Entity){
        viewModelScope.launch {
            if (todoList.id == 0) repository.addTodo(todoList) else repository.update(todoList)
        }
    }
}