package com.kilani.fizzbuzz_library.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Base ViewModel class to implement MVI (Model-View-Intent) pattern.
 * This pattern enables unidirectional data flow (View -> Intent -> State -> View)
 *
 * Same as for the Flux pattern, the State must be immutable for safety.
 *
 * Mutation of the state is done by copying the previous state, Flutter and React Native adopted this pattern for its efficiency
 * @param viewState Immutable state for the ViewModel
 * @param viewEvent Immutable one shot event for the ViewModel
 */
abstract class BaseMviViewModel<ST, VA, I> : ViewModel() {

    private val mutableViewStates: MutableLiveData<ST> = MutableLiveData()
    fun viewStates(): LiveData<ST> = mutableViewStates

    abstract var viewState: ST

    open fun dispatchIntent(intent: I) {}

    private val mutableViewActions: MutableViewModelEventLiveData<VA?> = MutableViewModelEventLiveData()

    fun viewActions(): ViewModelEventLiveData<VA?> = mutableViewActions

    private var viewAction: VA? = null
        get() = field
            ?: throw UninitializedPropertyAccessException("\"viewAction\" was queried before being initialized")
        set(value) {
            mutableViewActions.postEvent(value)
        }

    protected inline fun refreshState(block: ST.() -> Unit) {
        if (viewState != null) {
            viewState = viewState.apply(block)
            setState(viewState)
        }
    }

    protected inline fun <reified T : ST> refreshReifiedState(state: T? = null, block: T.() -> Unit) {
        if (viewState != null) {
            viewState = if (viewState is T) (viewState as T).apply(block)
            else state?.apply(block) ?: viewState
            setState(viewState)
        }
    }

    @Synchronized
    protected fun setState(state: ST) {
        viewModelScope.launch {
            state?.let {
                mutableViewStates.value = it
            }
        }
    }

    protected fun postAction(action: VA) {
        viewAction = action
    }
}
