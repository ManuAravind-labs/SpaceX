package com.android4you.space.presentation.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchesBinding
import com.android4you.space.domain.model.dynamic.CrewUIModel
import com.android4you.space.presentation.home.adapter.CrewAdapter
import com.android4you.space.utils.PeekingLinearLayoutManager

class CrewListViewHolder(private val binding: LayoutLaunchesBinding) :
    BaseViewHolder<CrewUIModel>(binding) {
    override fun bind(item: CrewUIModel) = with(binding) {
        val listAdapter = CrewAdapter()
        binding.homeRecyclerview.adapter = listAdapter
        binding.homeRecyclerview.layoutManager =
            PeekingLinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listAdapter.submitList(item.crewList)
        binding.titleTextview.text = item.title
    }
}
