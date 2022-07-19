package com.kilani.fizzbuzz_library.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class ViewModelEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

typealias MutableViewModelEventLiveData<T> = MutableLiveData<ViewModelEvent<T>>
typealias ViewModelEventLiveData<T> = LiveData<ViewModelEvent<T>>

fun <T> MutableLiveData<ViewModelEvent<T>>.postEvent(data: T) {
    this.postValue(ViewModelEvent(data))
}

fun <T> MutableLiveData<ViewModelEvent<T>>.setEventFromMain(data: T) {
    MainScope().launch {
        this@setEventFromMain.value = ViewModelEvent(data)
    }
}
