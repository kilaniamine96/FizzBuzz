package com.kilani.fizzbuzz.ui.results

import by.kirich1409.viewbindingdelegate.viewBinding
import com.kilani.fizzbuzz.R
import com.kilani.fizzbuzz.databinding.FragmentResultsBinding
import com.kilani.fizzbuzz_library.ui.BaseMviFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FizzBuzzResultsFragment :
    BaseMviFragment<FizzBuzzResultsState, FizzBuzzResultsAction, FizzBuzzResultsIntent>(R.layout.fragment_results) {
    override val viewModel by viewModel<FizzBuzzResultsViewModel>()

    private val binding by viewBinding<FragmentResultsBinding>()

    override fun renderViewState(viewState: FizzBuzzResultsState) {
        with(binding) {
            if (viewState.fizzBuzzList.isNotEmpty()) {
                fizzBuzzResultsList.adapter = FizzBuzzResultsAdapter(viewState.fizzBuzzList)
            }
        }
    }
}