package com.android4you.space.presentation.launches.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.databinding.LayoutLaunchDetailsBinding
import com.android4you.space.databinding.LayoutLaunchDetailsLaunchpadsBinding
import com.android4you.space.databinding.LayoutLaunchDetailsPayloadsBinding
import com.android4you.space.databinding.LayoutLaunchDetailsRocketInfoBinding
import com.android4you.space.domain.model.LaunchDetailsViewType
import com.android4you.space.domain.model.launchdetails.BaseDetailsUIModel
import com.android4you.space.domain.model.launchdetails.LaunchDetailsPayloadsUIModel
import com.android4you.space.domain.model.launchdetails.LaunchDetailsRocketUIModel
import com.android4you.space.domain.model.launchdetails.LaunchDetailsUIModel
import com.android4you.space.domain.model.launchdetails.LaunchPadUIModel
import com.android4you.space.presentation.launches.viewholder.launchdetails.BaseLaunchDetailsViewHolder
import com.android4you.space.presentation.launches.viewholder.launchdetails.LaunchDetailsPadsViewHolder
import com.android4you.space.presentation.launches.viewholder.launchdetails.LaunchDetailsPayloadsViewHolder
import com.android4you.space.presentation.launches.viewholder.launchdetails.LaunchDetailsRocketViewHolder
import com.android4you.space.presentation.launches.viewholder.launchdetails.LaunchDetailsViewHolder
class LaunchDetailsAdapter : RecyclerView.Adapter<BaseLaunchDetailsViewHolder<BaseDetailsUIModel>>() {

    private var uiList = ArrayList<BaseDetailsUIModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseLaunchDetailsViewHolder<BaseDetailsUIModel> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LaunchDetailsViewType.LAUNCH.ordinal -> {
                val binding = LayoutLaunchDetailsBinding.inflate(inflater, parent, false)
                LaunchDetailsViewHolder(binding)
            }
            LaunchDetailsViewType.LAUNCHPADS.ordinal -> {
                val binding = LayoutLaunchDetailsLaunchpadsBinding.inflate(inflater, parent, false)
                LaunchDetailsPadsViewHolder(binding)
            }
            LaunchDetailsViewType.ROCKET.ordinal -> {
                val binding = LayoutLaunchDetailsRocketInfoBinding.inflate(inflater, parent, false)
                LaunchDetailsRocketViewHolder(binding)
            }
            LaunchDetailsViewType.PAYLOAD.ordinal -> {
                val binding = LayoutLaunchDetailsPayloadsBinding.inflate(inflater, parent, false)
                LaunchDetailsPayloadsViewHolder(binding)
            }
            else -> throw IllegalArgumentException("The viewtype value of $viewType is not supported")
        } as BaseLaunchDetailsViewHolder<BaseDetailsUIModel>
    }

    override fun onBindViewHolder(holder: BaseLaunchDetailsViewHolder<BaseDetailsUIModel>, position: Int) {
        holder.bind(uiList[position])
    }

    override fun getItemCount(): Int = uiList.size

    override fun getItemViewType(position: Int): Int {
        return when (uiList[position]) {
            is LaunchDetailsUIModel -> LaunchDetailsViewType.LAUNCH.ordinal
            is LaunchPadUIModel -> LaunchDetailsViewType.LAUNCHPADS.ordinal
            is LaunchDetailsRocketUIModel -> LaunchDetailsViewType.ROCKET.ordinal
            is LaunchDetailsPayloadsUIModel -> LaunchDetailsViewType.PAYLOAD.ordinal
            else -> -1
        }
    }

    fun setUIOneList(model: BaseDetailsUIModel) {
        model.let {
            this.uiList.add(it)
            notifyDataSetChanged()
        }
    }
    fun setUIList(uiListALL: List<BaseDetailsUIModel>?) {
        uiListALL?.let { it ->
            this.uiList.clear()
            this.uiList.addAll(it)
        }
        notifyDataSetChanged()
    }
}
