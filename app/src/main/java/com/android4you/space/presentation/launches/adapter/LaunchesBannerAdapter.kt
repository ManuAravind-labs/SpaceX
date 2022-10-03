package com.android4you.space.presentation.launches.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.presentation.launches.viewholder.LaunchesBannerViewHolder

class LaunchesBannerAdapter : ListAdapter<String, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LaunchesBannerViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workout = getItem(position)
        if (holder is LaunchesBannerViewHolder)
            holder.bind(workout)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<String> =
            object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String) =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: String, newItem: String) =
                    oldItem == newItem
            }
    }
}
