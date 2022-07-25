package com.example.taghive.data.remote

interface TagHiveApi {

    @GET("api/v1/public/countries")
    fun getCountries(): Observable<CountryWrapper>
}