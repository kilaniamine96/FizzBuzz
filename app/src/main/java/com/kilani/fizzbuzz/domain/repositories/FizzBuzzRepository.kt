package com.kilani.fizzbuzz.domain.repositories

interface FizzBuzzRepository {
    fun saveFizzBuzzList(fizzBuzzList: List<String>)
    fun getFizzBuzzList(): List<String>?
}