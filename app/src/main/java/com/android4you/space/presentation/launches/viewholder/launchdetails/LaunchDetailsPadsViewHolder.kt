package com.android4you.space.presentation.launches.viewholder.launchdetails

import com.android4you.space.databinding.LayoutLaunchDetailsLaunchpadsBinding
import com.android4you.space.domain.model.launchdetails.LaunchPadUIModel

class LaunchDetailsPadsViewHolder(private val binding: LayoutLaunchDetailsLaunchpadsBinding) :
    BaseLaunchDetailsViewHolder<LaunchPadUIModel>(binding) {
    override fun bind(item: LaunchPadUIModel) = with(binding) {
        this.launchPads = item.padsModel
        executePendingBindings()
    }
}
