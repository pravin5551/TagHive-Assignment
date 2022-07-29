package com.example.taghive.view.fragment

interface ItemClickListener {
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