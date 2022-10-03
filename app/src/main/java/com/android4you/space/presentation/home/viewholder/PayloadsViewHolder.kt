package com.android4you.space.presentation.home.viewholder

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchesBinding
import com.android4you.space.domain.model.dynamic.PayloadUIModel
import com.android4you.space.presentation.PayloadViewType
import com.android4you.space.presentation.payloads.adapter.PayLoadsAdapter

class PayloadsViewHolder(private val binding: LayoutLaunchesBinding) :
    BaseViewHolder<PayloadUIModel>(binding) {
    override fun bind(item: PayloadUIModel) = with(binding) {
        val listAdapter = PayLoadsAdapter(PayloadViewType.FROM_HOME)
        binding.homeRecyclerview.adapter = listAdapter
        binding.homeRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listAdapter.submitList(item.payloads)
        binding.titleTextview.text = item.title
    }
}
