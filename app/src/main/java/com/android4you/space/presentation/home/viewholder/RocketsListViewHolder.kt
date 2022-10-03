package com.android4you.space.presentation.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchesBinding
import com.android4you.space.domain.model.dynamic.RocketUIModel
import com.android4you.space.presentation.rockets.adapter.RocketsAdapter

class RocketsListViewHolder(private val binding: LayoutLaunchesBinding) :
    BaseViewHolder<RocketUIModel>(binding) {
    override fun bind(item: RocketUIModel) = with(binding) {
        val listAdapter = RocketsAdapter()
        binding.homeRecyclerview.adapter = listAdapter
        binding.homeRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listAdapter.submitList(item.rockets)
        binding.titleTextview.text = item.title
    }
}
