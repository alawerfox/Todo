package com.todoList

import com.todoList.db.Entity
import com.todoList.db.TodoDao
import kotlinx.coroutines.flow.Flow

class Repository(
    private val todoDao: TodoDao
) {

    suspend fun addTodo(todoList: Entity) {
        todoDao.insert(
            Entity(
                title = todoList.title,
                description = todoList.description,
                content = todoList.content
            )
        )
        updateTodo()
    }

    suspend fun deleteTodo(todoList: Entity) {
        todoDao.delete(todoList.title)
        updateTodo()
    }

    fun updateTodo(): Flow<List<Entity>> {
        return todoDao.findAll()
    }
}