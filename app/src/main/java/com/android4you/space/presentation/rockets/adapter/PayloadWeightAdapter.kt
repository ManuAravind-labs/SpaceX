package com.android4you.space.presentation.rockets.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.rockets.PayloadWeight
import com.android4you.space.presentation.rockets.viewholder.PayloadWeightViewHolder

class PayloadWeightAdapter : ListAdapter<PayloadWeight, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PayloadWeightViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workout = getItem(position)
        if (holder is PayloadWeightViewHolder)
            holder.bind(workout)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PayloadWeight> =
            object : DiffUtil.ItemCallback<PayloadWeight>() {
                override fun areItemsTheSame(oldItem: PayloadWeight, newItem: PayloadWeight) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: PayloadWeight, newItem: PayloadWeight) =
                    oldItem == newItem
            }
    }
}
