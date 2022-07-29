package com.example.taghive.view.fragment


interface ItemClickListener {

    //Interface to send data from one fragment to another
    fun onCryptoListClicked(

        name: String,
        baseAsset: String,
        quoteAsset: String,
        openPrice: String,
        lowPrice: String,
        highPrice: String,
        lastPrice: String,
        volume: String,
        bidPrice: String,
        askPrice: String,
    )
}