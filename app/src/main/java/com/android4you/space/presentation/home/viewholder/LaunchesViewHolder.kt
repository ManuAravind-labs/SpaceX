package com.android4you.space.presentation.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchesBinding
import com.android4you.space.domain.model.dynamic.LaunchUIModel
import com.android4you.space.presentation.launches.adapter.LaunchesAdapter

class LaunchesViewHolder(private val binding: LayoutLaunchesBinding) :
    BaseViewHolder<LaunchUIModel>(binding) {
    override fun bind(item: LaunchUIModel) = with(binding) {
        val listAdapter = LaunchesAdapter(false)
        binding.homeRecyclerview.apply {
            adapter = listAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            listAdapter.submitList(item.launches)
        }
        binding.titleTextview.text = item.title
    }
}
