package com.android4you.space.presentation.missions.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.databinding.LayoutLaunchDetailsLaunchpadsBinding
import com.android4you.space.domain.model.pads.LaunchPadsModel
import com.android4you.space.utils.makeGone

class LaunchPadsViewHolder(private val binding: LayoutLaunchDetailsLaunchpadsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LaunchPadsModel) {
        binding.apply {
            launchPads = item
            executePendingBindings()
        }
        binding.launchpadTitleview.makeGone()
    }

    companion object {
        fun from(parent: ViewGroup): LaunchPadsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                LayoutLaunchDetailsLaunchpadsBinding.inflate(layoutInflater, parent, false)
            return LaunchPadsViewHolder(binding)
        }
    }
}
