package com.android4you.space.presentation.rockets.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.presentation.rockets.viewholder.RocketsViewHolder

class RocketsAdapter : ListAdapter<RocketModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RocketsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workout = getItem(position)
        if (holder is RocketsViewHolder)
            holder.bind(workout)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<RocketModel> =
            object : DiffUtil.ItemCallback<RocketModel>() {
                override fun areItemsTheSame(oldItem: RocketModel, newItem: RocketModel) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: RocketModel, newItem: RocketModel) =
                    oldItem == newItem
            }
    }
}
