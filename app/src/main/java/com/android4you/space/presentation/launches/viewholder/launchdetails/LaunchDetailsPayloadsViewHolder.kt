package com.android4you.space.presentation.launches.viewholder.launchdetails

import androidx.recyclerview.widget.LinearLayoutManager
import com.android4you.space.databinding.LayoutLaunchDetailsPayloadsBinding
import com.android4you.space.domain.model.launchdetails.LaunchDetailsPayloadsUIModel
import com.android4you.space.presentation.PayloadViewType
import com.android4you.space.presentation.payloads.adapter.PayLoadsAdapter

class LaunchDetailsPayloadsViewHolder(private val binding: LayoutLaunchDetailsPayloadsBinding) :
    BaseLaunchDetailsViewHolder<LaunchDetailsPayloadsUIModel>(binding) {
    override fun bind(item: LaunchDetailsPayloadsUIModel) = with(binding) {
        this.payloads = item
        executePendingBindings()
        val listAdapter = PayLoadsAdapter(PayloadViewType.FROM_LAUNCH_DETAILS)
        binding.payloadRecyclerview.adapter = listAdapter
        binding.payloadRecyclerview.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        listAdapter.submitList(item.payloadsModel)
        binding.titleView.text = item.title
    }
}
