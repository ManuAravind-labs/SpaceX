package com.android4you.space.presentation.launches.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.databinding.ItemLaunchesBannerBinding

class LaunchesBannerViewHolder(private val binding: ItemLaunchesBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String) {
        binding.apply {
            image = item
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): LaunchesBannerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemLaunchesBannerBinding.inflate(layoutInflater, parent, false)
            return LaunchesBannerViewHolder(binding)
        }
    }
}
