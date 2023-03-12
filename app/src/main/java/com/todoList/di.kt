package com.todoList

import androidx.room.Room
import com.todoList.db.TodoDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dependency = module {
    single { androidContext().getSharedPreferences("todo", 0) }

    single { Repository(get(), get(), get()) }
    viewModel { MainViewModel(get()) }
    viewModel { RedactorViewModel(get())}
    viewModel {SplashViewModel()}
    single {
        val db = Room.databaseBuilder(androidContext(), TodoDatabase::class.java, "todolist")
            .build()

        db.todoDao()
    }
}