package com.kilani.fizzbuzz.data

import com.kilani.fizzbuzz.domain.repositories.FizzBuzzRepository

class FizzBuzzRepositoryImpl : FizzBuzzRepository {
    private var fizzBuzzList: List<String>? = null

    override fun saveFizzBuzzList(fizzBuzzList: List<String>) {
        this.fizzBuzzList = fizzBuzzList
    }

    override fun getFizzBuzzList(): List<String>? = fizzBuzzList
}