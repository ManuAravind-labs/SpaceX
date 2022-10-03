package com.android4you.space.presentation.launches.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.R
import com.android4you.space.databinding.ItemGridLaunchesBinding
import com.android4you.space.domain.model.LaunchModel

class LaunchGridItemViewHolder(private val binding: ItemGridLaunchesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LaunchModel) {
        binding.apply {
            launches = item
            executePendingBindings()
        }
        val bundle = Bundle()
        bundle.putParcelable("launch", item)
        binding.launchesGridContainer.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_homeFragment_to_launchDetailsFragment,
                bundle
            )
        )
    }

    companion object {
        fun from(parent: ViewGroup): LaunchGridItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemGridLaunchesBinding.inflate(layoutInflater, parent, false)
            return LaunchGridItemViewHolder(binding)
        }
    }
}
