package com.example.taghive.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taghive.R
import com.example.taghive.databinding.CyptoItemLayoutBinding
import com.example.taghive.domain.model.CryptoDataClassItem

class CryptoListAdapter(var dataList: List<CryptoDataClassItem>, var listner: ItemClickListener) :
    RecyclerView.Adapter<CryptoListAdapter.CryptoListViewHolder>() {
    private lateinit var binding: CyptoItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = CyptoItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CryptoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, listner)
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    class CryptoListViewHolder(
        var dataBinding: CyptoItemLayoutBinding
    ) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun bind(data: CryptoDataClassItem, listner: ItemClickListener) {
            dataBinding.txtCryptoSymbol.text = data.symbol
            dataBinding.cryptoCard.setOnClickListener {
                listner.onCryptoListClicked(
                    name = data.symbol.toString(),
                    baseAsset = data.baseAsset.toString(),
                    quoteAsset = data.quoteAsset.toString(),
                    openPrice = data.openPrice.toString(),
                    lowPrice = data.lowPrice.toString(),
                    highPrice = data.highPrice.toString(),
                    lastPrice = data.lastPrice.toString(),
                    volume = data.volume.toString(),
                    bidPrice = data.bidPrice.toString(),
                    askPrice = data.askPrice.toString(),

                    )
            }
        }

    }
}