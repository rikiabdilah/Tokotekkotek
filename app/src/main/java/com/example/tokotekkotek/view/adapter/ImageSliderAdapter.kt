package com.example.tokotekkotek.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokotekkotek.databinding.ItemImageSlideBinding
import com.example.tokotekkotek.model.ResponseDataSlidersItem

class ImageSliderAdapter(private val list : List<ResponseDataSlidersItem>) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>(){

    class ViewHolder(var binding : ItemImageSlideBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item : ResponseDataSlidersItem){
            with(binding){
                Glide.with(itemView)
                    .load(item.image)
                    .into(imgSlide)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemImageSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}