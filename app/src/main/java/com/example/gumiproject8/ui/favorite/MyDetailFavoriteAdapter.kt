package com.example.gumiproject8.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.DetailFavoriteItemBinding
import com.example.gumiproject8.databinding.DetailItemBinding
import com.example.gumiproject8.model.favorite.FavoriteListDetail
import com.example.gumiproject8.model.mymodel.BaseModel

class MyDetailFavoriteAdapter(val action: (FavoriteListDetail) -> Unit) :
    ListAdapter<FavoriteListDetail, MyDetailFavoriteAdapter.ViewHolder>(MyDetailFavoriteCallback()) {

    inner class ViewHolder(val binding: DetailFavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: FavoriteListDetail) {
            binding.data = model
            binding.executePendingBindings()
            binding.detailLayout.setOnClickListener {
                action(model)
            }
        }
    }

    class MyDetailFavoriteCallback : DiffUtil.ItemCallback<FavoriteListDetail>() {
        override fun areItemsTheSame(
            oldItem: FavoriteListDetail,
            newItem: FavoriteListDetail
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: FavoriteListDetail,
            newItem: FavoriteListDetail
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.detail_favorite_item, parent, false)
        val binding = DetailFavoriteItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}