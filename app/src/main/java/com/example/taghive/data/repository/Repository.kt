package com.example.taghive.data.repository



import com.example.taghive.domain.model.*
import io.reactivex.Observable

interface Repository {

    fun getListOfCryptos(): Observable<CryptoDataClass>
}