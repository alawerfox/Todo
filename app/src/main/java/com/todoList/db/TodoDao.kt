package com.todoList.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("select * from todoList")
    fun findAll(): Flow<List<Entity>>

    @Insert
    suspend fun insert(todoList: Entity)

    @Query("DELETE FROM todoList WHERE title = :title")
    suspend fun delete(title: String)
}