package com.kilani.fizzbuzz.ui.form

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kilani.fizzbuzz.R
import com.kilani.fizzbuzz.databinding.FragmentFormBinding
import com.kilani.fizzbuzz_library.ui.BaseMviFragment
import com.kilani.fizzbuzz_library.ui.ViewModelEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FizzBuzzFormFragment :
    BaseMviFragment<FizzBuzzFormState, FizzBuzzFormAction, FizzBuzzFormIntent>(R.layout.fragment_form) {
    override val viewModel by viewModel<FizzBuzzFormViewModel>()

    private val binding by viewBinding<FragmentFormBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() = with(binding) {
        submitButton.setOnClickListener {
            dispatchIntent(FizzBuzzFormIntent.SubmitFizzBuzz)
        }
        number1EditText.doOnTextChanged { text, _, _, _ ->
            val textString = text.toString()
            val number = if (textString.isEmpty()) -1 else Integer.parseInt(textString)
            dispatchIntent(
                FizzBuzzFormIntent.ChangeNumber1(number)
            )
        }
        number2EditText.doOnTextChanged { text, _, _, _ ->
            val textString = text.toString()
            val number = if (textString.isEmpty()) -1 else Integer.parseInt(textString)
            dispatchIntent(
                FizzBuzzFormIntent.ChangeNumber2(number)
            )
        }
        word1EditText.doOnTextChanged { text, _, _, _ ->
            dispatchIntent(
                FizzBuzzFormIntent.ChangeWord1(
                    text.toString()
                )
            )
        }
        word2EditText.doOnTextChanged { text, _, _, _ ->
            dispatchIntent(
                FizzBuzzFormIntent.ChangeWord2(
                    text.toString()
                )
            )
        }
        limitEditText.doOnTextChanged { text, _, _, _ ->
            val textString = text.toString()
            val limit = if (textString.isEmpty()) -1 else Integer.parseInt(textString)
            dispatchIntent(
                FizzBuzzFormIntent.ChangeLimit(limit)
            )
        }
    }

    override fun renderViewState(viewState: FizzBuzzFormState)  {
        with(binding) {
            with(viewState) {
                number1TextInput.error = (number1 as? FormField.Error)?.errorData
                number2TextInput.error = (number2 as? FormField.Error)?.errorData
                word1TextInput.error = (word1 as? FormField.Error)?.errorData
                word2TextInput.error = (word2 as? FormField.Error)?.errorData
                limitTextInput.error = (limit as? FormField.Error)?.errorData
            }
        }
    }

    override fun renderViewAction(viewModelEvent: ViewModelEvent<FizzBuzzFormAction?>) {
        viewModelEvent.getContentIfNotHandled()?.let { action ->
            when (action) {
                FizzBuzzFormAction.ShowResults -> this.findNavController().navigate(R.id.action_fizzBuzzFormFragment_to_fizzBuzzResultsFragment)
            }
        }
    }
}