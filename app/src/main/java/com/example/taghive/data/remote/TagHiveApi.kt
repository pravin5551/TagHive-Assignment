package com.example.taghive.data.remote


import com.example.taghive.domain.model.*
import io.reactivex.Observable
import retrofit2.http.*


/**
 * This is interface for api call end pints
 */
interface TagHiveApi {

    @GET("sapi/v1/tickers/24hr")
    fun getCryptoList(): Observable<CryptoDataClass>
}