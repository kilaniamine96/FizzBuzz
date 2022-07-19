package com.kilani.fizzbuzz.domain.usecases

import com.kilani.fizzbuzz.domain.repositories.FizzBuzzRepository

class GetFizzBuzzList(private val fizzBuzzRepository: FizzBuzzRepository) {
    suspend operator fun invoke(): List<String>? = fizzBuzzRepository.getFizzBuzzList()
}