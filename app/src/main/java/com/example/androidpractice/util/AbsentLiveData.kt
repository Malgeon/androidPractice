package com.example.androidpractice.util

import androidx.lifecycle.LiveData


/**
 * LiveData가 null이 되도록 만들어준다.
 */

class AbsentLiveData<T : Any?> private constructor(): LiveData<T>() {
    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}