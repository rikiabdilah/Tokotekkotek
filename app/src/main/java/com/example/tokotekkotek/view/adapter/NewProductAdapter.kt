package com.example.tokotekkotek.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tokotekkotek.databinding.ItemProductBinding
import com.example.tokotekkotek.model.ResponseDataProductItem

class NewProductAdapter(private var list : List<ResponseDataProductItem>) : RecyclerView.Adapter<NewProductAdapter.ViewHolder>() {

    var onClickItemNewProduct : ((ResponseDataProductItem)->Unit)? = null

    class ViewHolder(var binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(item : ResponseDataProductItem){
            binding.itemProduct = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(list[position])
    }

    fun setDataListNewProduct(listProduct : List<ResponseDataProductItem>){
        list = listProduct
        notifyDataSetChanged()
    }
}