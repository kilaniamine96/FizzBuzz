package com.kilani.fizzbuzz.di

import com.kilani.fizzbuzz.data.FizzBuzzRepositoryImpl
import com.kilani.fizzbuzz.domain.repositories.FizzBuzzRepository
import com.kilani.fizzbuzz.domain.usecases.GetFizzBuzzList
import com.kilani.fizzbuzz.domain.usecases.SaveFizzBuzzList
import com.kilani.fizzbuzz.ui.form.FizzBuzzFormViewModel
import com.kilani.fizzbuzz.ui.results.FizzBuzzResultsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fizzBuzzModule = module {
    // Use Cases
    factory { SaveFizzBuzzList(fizzBuzzRepository = get()) }
    factory { GetFizzBuzzList(fizzBuzzRepository = get()) }

    // Repositories
    single<FizzBuzzRepository> { FizzBuzzRepositoryImpl() }

    // View Models
    viewModel { FizzBuzzFormViewModel(saveFizzBuzzList = get()) }
    viewModel { FizzBuzzResultsViewModel(getFizzBuzzList = get()) }
}