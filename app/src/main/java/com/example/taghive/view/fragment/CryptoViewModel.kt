package com.example.taghive.view.fragment

import androidx.lifecycle.MutableLiveData
import com.example.taghive.data.remote.CallbackSingleWrapper
import com.example.taghive.domain.model.CryptoDataClass
import com.example.taghive.domain.model.CryptoDataClassItem
import com.example.taghive.view.base.BaseViewModel

class CryptoViewModel : BaseViewModel() {
    private val cryptoDataList: MutableLiveData<CryptoDataClassItem> = MutableLiveData()
    fun getCryptoDatResponse() = cryptoDataList


    fun listOfCryptos() {
        loading.postValue(true)
        disposable = repository.getListOfCryptos()
            .subscribeWith(object : CallbackSingleWrapper<CryptoDataClassItem>() {
                override fun onApiSuccess(result: CryptoDataClassItem) {
                    cryptoDataList.postValue(result)
                    loading.postValue(false)
                }

                override fun onApiFailure(error: String) {
                    apiErrorLiveData.postValue(error)
                    loading.postValue(false)
                }

            })
    }
}