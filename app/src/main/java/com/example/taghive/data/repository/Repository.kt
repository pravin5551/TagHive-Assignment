package com.example.taghive.data.repository


import com.example.taghive.domain.model.CryptoDataClass
import com.example.taghive.domain.model.CryptoDataClassItem
import io.reactivex.Observable

interface Repository {

    fun getListOfCryptos(): Observable<CryptoDataClassItem>
}