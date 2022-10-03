package com.android4you.space.presentation.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.presentation.home.viewholder.CrewViewHolder

class CrewAdapter : ListAdapter<CrewModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CrewViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)
        if (holder is CrewViewHolder)
            holder.bind(model)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CrewModel> =
            object : DiffUtil.ItemCallback<CrewModel>() {
                override fun areItemsTheSame(oldItem: CrewModel, newItem: CrewModel) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: CrewModel, newItem: CrewModel) =
                    oldItem == newItem
            }
    }
}
