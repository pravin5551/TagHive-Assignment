package com.example.taghive.data.repository



import com.example.taghive.domain.model.*
import io.reactivex.Observable

/**
 * Interface for repository of mvvm architecture to call api
 */
interface Repository {

    fun getListOfCryptos(): Observable<CryptoDataClass>
}