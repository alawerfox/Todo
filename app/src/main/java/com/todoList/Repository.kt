package com.todoList

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.todoList.db.Entity
import com.todoList.db.TodoDao
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val FIRST_START = "first_start"

class Repository(
    private val todoDao: TodoDao,
    private val sharedPreferences: SharedPreferences,
    context: Context
) {

    private val coroutineScope = MainScope()

    init {
        coroutineScope.launch {
            val isFirstStart = sharedPreferences.getBoolean(FIRST_START, true)

            if (isFirstStart) {
                todoDao.insert(
                    Entity(
                        title = context.getText(R.string.title_default).toString(),
                        description = context.getText(R.string.description_default).toString()
                    )
                )

                sharedPreferences.edit {
                    putBoolean(FIRST_START, false)
                }
            }
        }
    }

    suspend fun addTodo(todo: Entity) {
        todoDao.insert(todo
        )
    }

    suspend fun deleteTodo(todoList: Entity) {
        todoDao.delete(todoList.title)
    }

    fun findAll(): Flow<List<Entity>> {
        return todoDao.findAll()
    }

    suspend fun update(todoList: Entity){
        todoDao.update(todoList)
    }
}