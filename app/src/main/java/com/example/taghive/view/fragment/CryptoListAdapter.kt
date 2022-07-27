package com.example.taghive.view.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taghive.R
import com.example.taghive.domain.model.CryptoDataClassItem

class CryptoListAdapter(var dataList: List<CryptoDataClassItem>, var listner: ItemClickListener) :
    RecyclerView.Adapter<CryptoListAdapter.CryptoListViewHolder>() {


    class CryptoListViewHolder(itemView: View, var listner: ItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

//        fun bind(data: CryptoDataClassItem, listner: ItemClickListener) {
            val titleTextView: TextView = itemView.findViewById(R.id.txt_crypto_symbol)
//        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        return CryptoListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cypto_item_layout, parent, false),
            listner
        )
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        val data = dataList[position]
        holder.titleTextView.text = data.symbol
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }
}