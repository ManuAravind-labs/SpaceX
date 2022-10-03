package com.android4you.space.presentation.launches.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.R
import com.android4you.space.databinding.ItemListLaunchesBinding
import com.android4you.space.domain.model.LaunchModel

class LaunchListItemViewHolder(private val binding: ItemListLaunchesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LaunchModel) {
        binding.apply {
            launches = item
            executePendingBindings()
        }
        val bundle = Bundle()
        bundle.putParcelable("launch", item)
        binding.launchItemContainer.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_launchesFragment_to_launchDetailsFragment,
                bundle
            )
        )
    }

    companion object {
        fun from(parent: ViewGroup): LaunchListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListLaunchesBinding.inflate(layoutInflater, parent, false)
            return LaunchListItemViewHolder(binding)
        }
    }
}
