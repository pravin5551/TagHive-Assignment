package com.example.taghive.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taghive.data.repository.Repository
import io.reactivex.disposables.Disposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


open class BaseViewModel : ViewModel(), KoinComponent {

    protected val repository: Repository by inject()
    protected var disposable: Disposable? = null


    protected val loading: MutableLiveData<Boolean> = MutableLiveData()
    fun getloading() = loading

    protected val apiErrorLiveData = MutableLiveData<String>()
    fun getErrorLiveData() = apiErrorLiveData


}