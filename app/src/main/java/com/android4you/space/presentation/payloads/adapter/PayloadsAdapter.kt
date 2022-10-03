package com.android4you.space.presentation.payloads.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.presentation.PayloadViewType
import com.android4you.space.presentation.payloads.viewholder.PayloadsGridItemViewHolder

private const val LIST_VIEW_TYPE = 0
private const val GRID_VIEW_TYPE = 1

class PayLoadsAdapter(
    val type: PayloadViewType = PayloadViewType.FROM_HOME
) :
    ListAdapter<PayloadsModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LIST_VIEW_TYPE) {
            PayloadsGridItemViewHolder.from(parent)
        } else {
            PayloadsGridItemViewHolder.from(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = getItem(position)
        if (holder is PayloadsGridItemViewHolder)
            holder.bind(model, type)
        else (holder as PayloadsGridItemViewHolder).bind(model, type)
    }

    override fun getItemViewType(position: Int): Int {
        return when (type) {
            PayloadViewType.FROM_HOME -> {
                GRID_VIEW_TYPE
            }
            PayloadViewType.FROM_LIST -> {
                LIST_VIEW_TYPE
            }
            PayloadViewType.FROM_LAUNCH_DETAILS -> {
                GRID_VIEW_TYPE
            }
        }
        //  return if (isListView) LIST_VIEW_TYPE else GRID_VIEW_TYPE
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PayloadsModel> =
            object : DiffUtil.ItemCallback<PayloadsModel>() {
                override fun areItemsTheSame(oldItem: PayloadsModel, newItem: PayloadsModel) =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: PayloadsModel, newItem: PayloadsModel) =
                    oldItem == newItem
            }
    }
}
