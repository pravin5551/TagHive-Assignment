package com.example.taghive.data.di

import com.example.taghive.data.local.PreferenceHelper
import com.example.taghive.data.repository.Repository
import com.example.taghive.data.repository.RepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {

    single<Repository> { RepositoryImpl(get(), get()) }

    single { PreferenceHelper(androidContext()) }
}