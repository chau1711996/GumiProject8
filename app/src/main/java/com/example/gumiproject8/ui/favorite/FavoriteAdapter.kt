package com.example.gumiproject8.ui.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.FavoriteItemBinding
import com.example.gumiproject8.model.favorite.FavoriteEntity

class FavoriteAdapter(val action: (FavoriteEntity) -> Unit) :
    ListAdapter<FavoriteEntity, FavoriteAdapter.ViewHolder>(FavoriteCallback()) {

    inner class ViewHolder(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteEntity: FavoriteEntity) {
            binding.data = favoriteEntity
            binding.executePendingBindings()
            binding.productItemLayout.setOnClickListener {
                action(favoriteEntity)
            }
        }
    }

    class FavoriteCallback : DiffUtil.ItemCallback<FavoriteEntity>() {
        override fun areItemsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoriteEntity, newItem: FavoriteEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.favorite_item, parent, false)
        val binding = FavoriteItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}