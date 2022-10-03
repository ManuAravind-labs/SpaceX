package com.android4you.space.presentation.launches.viewholder.launchdetails

import com.android4you.space.databinding.LayoutLaunchDetailsBinding
import com.android4you.space.domain.model.launchdetails.LaunchDetailsUIModel

class LaunchDetailsViewHolder(private val binding: LayoutLaunchDetailsBinding) :
    BaseLaunchDetailsViewHolder<LaunchDetailsUIModel>(binding) {
    override fun bind(item: LaunchDetailsUIModel) = with(binding) {
        this.launches = item.launchModel
        executePendingBindings()
    }
}
