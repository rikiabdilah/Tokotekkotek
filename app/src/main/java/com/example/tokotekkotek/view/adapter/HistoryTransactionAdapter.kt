package com.example.tokotekkotek.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokotekkotek.databinding.ItemHistoryTransactionBinding
import com.example.tokotekkotek.model.ResponseDataProductItem
import com.example.tokotekkotek.model.ResponseUserTransHistoryItem
import java.text.NumberFormat
import java.util.*

class HistoryTransactionAdapter(private var list : List<ResponseUserTransHistoryItem>) : RecyclerView.Adapter<HistoryTransactionAdapter.ViewHolder>() {


    class ViewHolder(var binding : ItemHistoryTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(item : ResponseUserTransHistoryItem){
            binding.itemHistory = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHistoryTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(list[position])

        Glide.with(holder.itemView)
            .load(list[position].picture)
            .into(holder.binding.imgProduct)

        holder.binding.tvTotal.text = convertToRupiah(list[position].total)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    fun setDataListNewProduct(listProduct : List<ResponseUserTransHistoryItem>){
        list = listProduct
        notifyDataSetChanged()
    }

    fun convertToRupiah(number: Int): String{

        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}