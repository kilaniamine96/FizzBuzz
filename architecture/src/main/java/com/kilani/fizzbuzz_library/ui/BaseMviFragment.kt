package com.kilani.fizzbuzz_library.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseMviFragment<ST, VA, I>(@LayoutRes layoutId: Int? = null) : Fragment(layoutId ?: -1) {
    protected abstract val viewModel: BaseMviViewModel<ST, VA, I>

    private val viewStateObserver = Observer<ST> {
        renderViewState(it)
    }
    private val viewActionObserver = Observer<ViewModelEvent<VA?>> {
        renderViewAction(it)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewStates().observe(viewLifecycleOwner, viewStateObserver)
        viewModel.viewActions().observe(viewLifecycleOwner, viewActionObserver)
    }

    protected fun dispatchIntent(intent: I) {
        viewModel.dispatchIntent(intent)
    }

    protected open fun renderViewState(viewState: ST) {}

    protected open fun renderViewAction(viewModelEvent: ViewModelEvent<VA?>) {}
}