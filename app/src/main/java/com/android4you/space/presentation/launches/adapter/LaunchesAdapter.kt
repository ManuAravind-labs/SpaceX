package com.android4you.space.presentation.launches.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.presentation.launches.viewholder.LaunchGridItemViewHolder
import com.android4you.space.presentation.launches.viewholder.LaunchListItemViewHolder

private const val LIST_VIEW_TYPE = 0
private const val GRID_VIEW_TYPE = 1

class LaunchesAdapter(private val isListView: Boolean) :
    ListAdapter<LaunchModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LIST_VIEW_TYPE) {
            LaunchListItemViewHolder.from(parent)
        } else {
            LaunchGridItemViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)
        if (holder is LaunchListItemViewHolder)
            holder.bind(model)
        else (holder as LaunchGridItemViewHolder).bind(model)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isListView) LIST_VIEW_TYPE else GRID_VIEW_TYPE
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<LaunchModel> =
            object : DiffUtil.ItemCallback<LaunchModel>() {
                override fun areItemsTheSame(oldItem: LaunchModel, newItem: LaunchModel) =
                    oldItem.flight_number == newItem.flight_number

                override fun areContentsTheSame(oldItem: LaunchModel, newItem: LaunchModel) =
                    oldItem == newItem
            }
    }
}
