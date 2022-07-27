package com.example.taghive.data.repository


import com.example.taghive.data.local.PreferenceHelper
import com.example.taghive.data.remote.TagHiveApi
import com.example.taghive.domain.model.CryptoDataClass
import com.example.taghive.domain.model.CryptoDataClassItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl constructor(
    private val api: TagHiveApi,
    private val preferenceHelper: PreferenceHelper
) : Repository {
    fun <T> subscribeOnIoThread(observable: Observable<T>) =
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getListOfCryptos(): Observable<CryptoDataClassItem> {
        return subscribeOnIoThread(api.getCryptoList())
    }

}