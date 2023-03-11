package com.todoList.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoList")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    val title: String,
    val description: String,
    val content: String,
)