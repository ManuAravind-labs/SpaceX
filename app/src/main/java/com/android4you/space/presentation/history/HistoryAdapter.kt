package com.android4you.space.presentation.history

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.history.HistoryModel

class HistoryAdapter : ListAdapter<HistoryModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val workout = getItem(position)
        if (holder is HistoryViewHolder)
            holder.bind(workout)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<HistoryModel> =
            object : DiffUtil.ItemCallback<HistoryModel>() {
                override fun areItemsTheSame(oldItem: HistoryModel, newItem: HistoryModel) =
                    oldItem.title == newItem.title

                override fun areContentsTheSame(oldItem: HistoryModel, newItem: HistoryModel) =
                    oldItem == newItem
            }
    }
}
