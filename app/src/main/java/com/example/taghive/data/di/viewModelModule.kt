package com.example.taghive.data.di

import com.example.taghive.view.base.BaseViewModel
import com.example.taghive.view.fragment.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
/**
 * This is an viewModel module where module of BaseViewModel and CryptoViewModel is created
 */
val viewModelModule = module {
    viewModel { BaseViewModel() }
    viewModel { CryptoViewModel() }
}