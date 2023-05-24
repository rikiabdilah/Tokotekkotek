package com.example.tokotekkotek.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tokotekkotek.R
import com.example.tokotekkotek.data.local.entity.FavoriteEntity
import com.example.tokotekkotek.databinding.ItemFavoriteBinding

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<FavoriteEntity>(){
        override fun areItemsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean =
            oldItem.id_barang == newItem.id_barang

        override fun areContentsTheSame(
            oldItem: FavoriteEntity,
            newItem: FavoriteEntity
        ): Boolean =
            oldItem == newItem
    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder =
        ListViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

    inner class ListViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteEntity) {
            val imageUrl = "https://image.tmdb.org/t/p/w500"
            binding.name.text = item.title
            Glide.with(itemView).load(imageUrl + item.photo).into(binding.photo)
            binding.root.setOnClickListener {
                it.findNavController()
                    .navigate(
                        R.id.action_favoriteFragment_to_detailFragment,
                        Bundle().apply { putInt("MOVIE_ID", item.id_barang) })
            }
        }
    }
}