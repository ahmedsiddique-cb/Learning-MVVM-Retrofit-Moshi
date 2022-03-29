package com.example.hiltmvvmflowmoshi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.hiltmvvmflowmoshi.data.Dogs
import com.example.hiltmvvmflowmoshi.databinding.EachRowBinding
import javax.inject.Inject

class DogsAdapter
    @Inject
    constructor():
    PagingDataAdapter<Dogs, DogsAdapter.DogsViewHolder>(Diff) {
    class DogsViewHolder(private val binding: EachRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dogs: Dogs) {
            binding.apply {
                image.load(dogs.url)
            }
        }
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dog = getItem(position)
        if (dog != null) {
            holder.bind(dog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    object Diff : DiffUtil.ItemCallback<Dogs>() {
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean = oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean = oldItem == newItem

    }
}