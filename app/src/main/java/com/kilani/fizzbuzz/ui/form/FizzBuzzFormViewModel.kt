package com.kilani.fizzbuzz.ui.form

import androidx.lifecycle.viewModelScope
import com.kilani.fizzbuzz.domain.usecases.SaveFizzBuzzList
import com.kilani.fizzbuzz_library.ui.BaseMviViewModel
import kotlinx.coroutines.launch

internal class FizzBuzzFormViewModel(private val saveFizzBuzzList: SaveFizzBuzzList) :
    BaseMviViewModel<FizzBuzzFormState, FizzBuzzFormAction, FizzBuzzFormIntent>() {
    override var viewState = FizzBuzzFormState()

    override fun dispatchIntent(intent: FizzBuzzFormIntent) {
        when (intent) {
            is FizzBuzzFormIntent.ChangeNumber1 -> refreshState {
                number1 = FormField.Valid(intent.number1)
            }
            is FizzBuzzFormIntent.ChangeNumber2 -> refreshState {
                number2 = FormField.Valid(intent.number2)
            }
            is FizzBuzzFormIntent.ChangeWord1 -> refreshState {
                word1 = FormField.Valid(intent.word1)
            }
            is FizzBuzzFormIntent.ChangeWord2 -> refreshState {
                word2 = FormField.Valid(intent.word2)
            }
            is FizzBuzzFormIntent.ChangeLimit -> refreshState {
                limit = FormField.Valid(intent.limit)
            }
            FizzBuzzFormIntent.SubmitFizzBuzz -> submitFizzBuzz()
        }
    }

    private fun submitFizzBuzz() = with(viewState) {
        refreshState {
            number1 = checkInt(number1)
            number2 = checkInt(number2)
            word1 = checkString(word1)
            word2 = checkString(word2)
            limit = checkInt(limit)
        }
        if (checkForm(number1, number2, word1, word2, limit)) {
            viewModelScope.launch {
                saveFizzBuzzList(number1.data, number2.data, word1.data, word2.data, limit.data)
                postAction(FizzBuzzFormAction.ShowResults)
            }
        }
    }

    private fun checkString(value: FormField<String, String>): FormField<String, String> {
        return if (value is FormField.Valid) {
            if (value.data.isNotEmpty()) value
            else FormField.Error(value.data, "String is empty")
        } else {
            value
        }
    }

    private fun checkInt(value: FormField<Int, String>): FormField<Int, String> {
        return if (value is FormField.Valid) {
            when {
                value.data == -1 -> FormField.Error(value.data, "Number is empty")
                value.data > 0 -> value
                else -> FormField.Error(value.data, "Number must be > 0")
            }
        } else {
            value
        }
    }

    private fun checkForm(vararg formFields: FormField<*, *>): Boolean {
        var isValid = true
        formFields.forEach {
            if (it is FormField.Error) {
                isValid = false
            }
        }
        return isValid
    }
}

internal data class FizzBuzzFormState(
    var number1: FormField<Int, String> = FormField.Valid(-1),
    var number2: FormField<Int, String> = FormField.Valid(-1),
    var word1: FormField<String, String> = FormField.Valid(""),
    var word2: FormField<String, String> = FormField.Valid(""),
    var limit: FormField<Int, String> = FormField.Valid(-1),
)

internal sealed class FizzBuzzFormIntent {
    data class ChangeNumber1(val number1: Int) : FizzBuzzFormIntent()
    data class ChangeNumber2(val number2: Int) : FizzBuzzFormIntent()
    data class ChangeWord1(val word1: String) : FizzBuzzFormIntent()
    data class ChangeWord2(val word2: String) : FizzBuzzFormIntent()
    data class ChangeLimit(val limit: Int) : FizzBuzzFormIntent()
    object SubmitFizzBuzz : FizzBuzzFormIntent()
}

internal sealed class FizzBuzzFormAction {
    object ShowResults : FizzBuzzFormAction()
}

internal sealed class FormField<T, E> {
    abstract val data: T

    data class Valid<T, E>(override val data: T) : FormField<T, E>()
    data class Error<T, E>(override val data: T, val errorData: E) : FormField<T, E>()
}