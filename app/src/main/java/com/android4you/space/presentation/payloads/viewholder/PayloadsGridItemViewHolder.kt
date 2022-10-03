package com.android4you.space.presentation.payloads.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.R
import com.android4you.space.databinding.LayoutPayloadsBinding
import com.android4you.space.domain.model.payloads.PayloadsModel
import com.android4you.space.presentation.PayloadViewType

class PayloadsGridItemViewHolder(private val binding: LayoutPayloadsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PayloadsModel, type: PayloadViewType) {
        binding.apply {
            payloads = item
            executePendingBindings()
        }

        val bundle = Bundle()
        bundle.putParcelable("payload", item)
        when (type) {
            PayloadViewType.FROM_HOME -> binding.cardView.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_homeFragment_to_payloadDetailsFragment,
                    bundle
                )
            )
            PayloadViewType.FROM_LIST -> binding.cardView.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_payloadsFragment_to_payloadDetailsFragment,
                    bundle
                )
            )
            PayloadViewType.FROM_LAUNCH_DETAILS -> binding.cardView.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.action_launchDetailsFragment_to_payloadDetailsFragment,
                    bundle
                )
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): PayloadsGridItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutPayloadsBinding.inflate(layoutInflater, parent, false)
            return PayloadsGridItemViewHolder(binding)
        }
    }
}
