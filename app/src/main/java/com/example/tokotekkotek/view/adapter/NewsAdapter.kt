package com.example.tokotekkotek.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokotekkotek.databinding.ItemNewsBinding
import com.example.tokotekkotek.model.ResponseNewsUpdateItem

class NewsAdapter(private var list : List<ResponseNewsUpdateItem>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    var onClickItemNews : ((ResponseNewsUpdateItem)-> Unit)? = null

    class ViewHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item : ResponseNewsUpdateItem){
            binding.itemNews = item

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])

        Glide.with(holder.itemView)
            .load(list[position].newsImage)
            .into(holder.binding.imgNews)

        holder.binding.itemCardNews.setOnClickListener {
            onClickItemNews?.invoke(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setDataNews(listNews : List<ResponseNewsUpdateItem>){
        list = listNews
        notifyDataSetChanged()
    }
}