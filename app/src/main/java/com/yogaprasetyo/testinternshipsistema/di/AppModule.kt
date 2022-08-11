package com.yogaprasetyo.testinternshipsistema.di

import com.yogaprasetyo.testinternshipsistema.detail.DetailViewModel
import com.yogaprasetyo.testinternshipsistema.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}