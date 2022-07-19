package com.kilani.fizzbuzz.ui.results

import androidx.lifecycle.viewModelScope
import com.kilani.fizzbuzz.domain.usecases.GetFizzBuzzList
import com.kilani.fizzbuzz_library.ui.BaseMviViewModel
import kotlinx.coroutines.launch

internal class FizzBuzzResultsViewModel(private val getFizzBuzzList: GetFizzBuzzList) :
    BaseMviViewModel<FizzBuzzResultsState, FizzBuzzResultsAction, FizzBuzzResultsIntent>() {
    override var viewState = FizzBuzzResultsState()
    init {
        viewModelScope.launch {
            getFizzBuzzList()?.let {
                refreshState {
                    fizzBuzzList = it
                }
            }
        }
    }

}

data class FizzBuzzResultsState(
    var fizzBuzzList: List<String> = listOf()
)
sealed class FizzBuzzResultsAction
sealed class FizzBuzzResultsIntent
