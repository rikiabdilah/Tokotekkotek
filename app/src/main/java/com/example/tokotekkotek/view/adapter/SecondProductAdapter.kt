package com.example.tokotekkotek.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokotekkotek.databinding.ItemProductBinding
import com.example.tokotekkotek.model.ResponseDataProductItem

class SecondProductAdapter(private var list : List<ResponseDataProductItem>) : RecyclerView.Adapter<SecondProductAdapter.ViewHolder>() {

    var onClickItemSecondProduct : ((ResponseDataProductItem)->Unit)? = null

    class ViewHolder(var binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(item : ResponseDataProductItem){
            binding.itemProduct = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(list[position])

        Glide.with(holder.itemView)
            .load(list[position].productImage)
            .into(holder.binding.imgProduct)

        holder.binding.itemCardProduct.setOnClickListener {
            onClickItemSecondProduct?.invoke(list[position])
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }


    fun setDataListSecondProduct(listProduct : List<ResponseDataProductItem>){
        list = listProduct
        notifyDataSetChanged()
    }
}