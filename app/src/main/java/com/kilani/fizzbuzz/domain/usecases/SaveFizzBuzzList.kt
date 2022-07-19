package com.kilani.fizzbuzz.domain.usecases

import com.kilani.fizzbuzz.domain.repositories.FizzBuzzRepository

class SaveFizzBuzzList(private val fizzBuzzRepository: FizzBuzzRepository) {
    suspend operator fun invoke(
        number1: Int,
        number2: Int,
        word1: String,
        word2: String,
        limit: Int
    ) {
        val fizzBuzzList = mutableListOf<String>()
        for (i in 1..limit) {
            val stringItem: String = when {
                i.isMultipleOf(number1.times(number2)) -> "$word1$word2"
                i.isMultipleOf(number1) -> word1
                i.isMultipleOf(number2) -> word2
                else -> i.toString()
            }
            fizzBuzzList.add(stringItem)
        }
        fizzBuzzRepository.saveFizzBuzzList(fizzBuzzList)
    }

    private fun Int.isMultipleOf(number: Int) = this % number == 0
}