package com.todoList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
) : ViewModel() {

    private val timeToAppLoaded = 600L
    val progressLiveData = MutableLiveData<Int>()

    private var loadingJob: Job? = null

    fun showProgress(){
        loadingJob = viewModelScope.launch {
            val step = timeToAppLoaded / 100

            for (i in 0..100) {
                progressLiveData.postValue(i)
                delay(step)
            }


        }
    }

    override fun onCleared() {
        loadingJob?.apply {
            cancelChildren()
            cancel()
        }
        super.onCleared()
    }
}