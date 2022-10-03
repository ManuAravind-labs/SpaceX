package com.android4you.space.presentation.rockets.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.databinding.LayoutPayloadWeightBinding
import com.android4you.space.domain.model.rockets.PayloadWeight

class PayloadWeightViewHolder(private val binding: LayoutPayloadWeightBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PayloadWeight) {
        binding.apply {
            payloadWeight = item
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): PayloadWeightViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutPayloadWeightBinding.inflate(layoutInflater, parent, false)
            return PayloadWeightViewHolder(binding)
        }
    }
}
