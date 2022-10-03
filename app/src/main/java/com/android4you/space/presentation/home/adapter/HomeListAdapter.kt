package com.android4you.space.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.databinding.LayoutLaunchesBinding
import com.android4you.space.domain.model.ResponseType
import com.android4you.space.domain.model.dynamic.BaseUIModel
import com.android4you.space.domain.model.dynamic.CrewUIModel
import com.android4you.space.domain.model.dynamic.HistoryUIModel
import com.android4you.space.domain.model.dynamic.LaunchUIModel
import com.android4you.space.domain.model.dynamic.PayloadUIModel
import com.android4you.space.domain.model.dynamic.RocketUIModel
import com.android4you.space.presentation.history.HistoryListViewHolder
import com.android4you.space.presentation.home.viewholder.BaseViewHolder
import com.android4you.space.presentation.home.viewholder.CrewListViewHolder
import com.android4you.space.presentation.home.viewholder.LaunchesViewHolder
import com.android4you.space.presentation.home.viewholder.PayloadsViewHolder
import com.android4you.space.presentation.home.viewholder.RocketsListViewHolder

@Suppress("UNCHECKED_CAST")
class HomeListAdapter : RecyclerView.Adapter<BaseViewHolder<BaseUIModel>>() {

    private var uiList = ArrayList<BaseUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseUIModel> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ResponseType.LAUNCH.ordinal -> {
                val binding = LayoutLaunchesBinding.inflate(inflater, parent, false)
                LaunchesViewHolder(binding)
            }
            ResponseType.ROCKET.ordinal -> {
                val binding = LayoutLaunchesBinding.inflate(inflater, parent, false)
                RocketsListViewHolder(binding)
            }
            ResponseType.CREW.ordinal -> {
                val binding = LayoutLaunchesBinding.inflate(inflater, parent, false)
                CrewListViewHolder(binding)
            }
            ResponseType.PAYLOAD.ordinal -> {
                val binding = LayoutLaunchesBinding.inflate(inflater, parent, false)
                PayloadsViewHolder(binding)
            }
            ResponseType.HISTORY.ordinal -> {
                val binding = LayoutLaunchesBinding.inflate(inflater, parent, false)
                HistoryListViewHolder(binding)
            }
            else -> throw IllegalArgumentException("The view type value of $viewType is not supported")
        } as BaseViewHolder<BaseUIModel>
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseUIModel>, position: Int) {
        holder.bind(uiList[position])
    }

    override fun getItemCount(): Int = uiList.size

    override fun getItemViewType(position: Int): Int {
        return when (uiList[position]) {
            is LaunchUIModel -> ResponseType.LAUNCH.ordinal
            is CrewUIModel -> ResponseType.CREW.ordinal
            is RocketUIModel -> ResponseType.ROCKET.ordinal
            is PayloadUIModel -> ResponseType.PAYLOAD.ordinal
            is HistoryUIModel -> ResponseType.HISTORY.ordinal
            else -> -1
        }
    }

    fun setUIOneList(model: BaseUIModel) {
        model.let {
            this.uiList.add(it)
            notifyDataSetChanged()
        }
    }

    fun setUIList(uiListALL: List<BaseUIModel>?) {
        uiListALL?.let { it ->
            this.uiList.clear()
            this.uiList.addAll(it)
        }
        notifyDataSetChanged()
    }
}
