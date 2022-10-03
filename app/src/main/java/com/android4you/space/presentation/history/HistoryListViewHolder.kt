package com.android4you.space.presentation.history

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchesBinding
import com.android4you.space.domain.model.dynamic.HistoryUIModel
import com.android4you.space.presentation.home.viewholder.BaseViewHolder

class HistoryListViewHolder(private val binding: LayoutLaunchesBinding) :
    BaseViewHolder<HistoryUIModel>(binding) {
    override fun bind(item: HistoryUIModel) = with(binding) {
        val listAdapter = HistoryAdapter()
        binding.homeRecyclerview.adapter = listAdapter
        binding.homeRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listAdapter.submitList(item.histories)
        binding.titleTextview.text = item.title
    }
}
