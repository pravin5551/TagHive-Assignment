package com.example.taghive.view.fragment

import androidx.lifecycle.MutableLiveData
import com.example.taghive.data.remote.CallbackSingleWrapper
import com.example.taghive.domain.model.CryptoDataClass
import com.example.taghive.domain.model.CryptoDataClassItem
import com.example.taghive.view.base.BaseViewModel

class CryptoViewModel : BaseViewModel() {
    private val cryptoDataList: MutableLiveData<CryptoDataClass> = MutableLiveData()
    fun getCryptoDatResponse() = cryptoDataList

    //Calling the List Of cryptos , and after observing data on response of failure and success showing data to user
    //It;s is just observing stream of Live data from server
    fun listOfCryptos() {
        loading.postValue(true)
        disposable = repository.getListOfCryptos()
            .subscribeWith(object : CallbackSingleWrapper<CryptoDataClass>() {
                override fun onApiSuccess(result: CryptoDataClass) {
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