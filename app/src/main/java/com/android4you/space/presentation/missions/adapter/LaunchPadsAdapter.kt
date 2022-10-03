package com.android4you.space.presentation.missions.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.presentation.missions.viewholder.LaunchPadsViewHolder

class LaunchPadsAdapter : ListAdapter<LaunchPadsModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LaunchPadsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workout = getItem(position)
        if (holder is LaunchPadsViewHolder)
            holder.bind(workout)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<LaunchPadsModel> =
            object : DiffUtil.ItemCallback<LaunchPadsModel>() {
                override fun areItemsTheSame(oldItem: LaunchPadsModel, newItem: LaunchPadsModel) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: LaunchPadsModel, newItem: LaunchPadsModel) =
                    oldItem == newItem
            }
    }
}