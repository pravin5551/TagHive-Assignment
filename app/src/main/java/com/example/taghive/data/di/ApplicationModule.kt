package com.example.taghive.data.di


import com.example.taghive.data.repository.Repository
import com.example.taghive.data.repository.RepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * This is an application module where singleton object of repo and Preferencehelper is created
 */
val applicationModule = module {

    single<Repository> { RepositoryImpl(get()) }

}