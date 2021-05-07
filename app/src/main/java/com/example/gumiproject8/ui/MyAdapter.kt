package com.example.gumiproject8.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.ProductItemBinding
import com.example.gumiproject8.model.mymodel.BaseModel

class MyAdapter(val action: (Int) -> Unit) :
    ListAdapter<BaseModel, MyAdapter.ViewHolder>(MyBaseModelCallback()) {

    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BaseModel) {
            binding.data = model
            binding.executePendingBindings()
            binding.productItemLayout.setOnClickListener {
                action(model.id)
            }
        }
    }

    class MyBaseModelCallback : DiffUtil.ItemCallback<BaseModel>() {
        override fun areItemsTheSame(
            oldItem: BaseModel,
            newItem: BaseModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BaseModel,
            newItem: BaseModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        val binding = ProductItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}