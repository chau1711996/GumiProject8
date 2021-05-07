package com.example.gumiproject8.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gumiproject8.R
import com.example.gumiproject8.databinding.DetailItemBinding
import com.example.gumiproject8.model.mymodel.BaseModel

class MyDetailAdapter(val action: (BaseModel) -> Unit) :
    ListAdapter<BaseModel, MyDetailAdapter.ViewHolder>(MyDetailBaseModelCallback()) {

    inner class ViewHolder(val binding: DetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BaseModel) {
            binding.data = model
            binding.executePendingBindings()
            binding.detailLayout.setOnClickListener {
                action(model)
            }
        }
    }

    class MyDetailBaseModelCallback : DiffUtil.ItemCallback<BaseModel>() {
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
            LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        val binding = DetailItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}