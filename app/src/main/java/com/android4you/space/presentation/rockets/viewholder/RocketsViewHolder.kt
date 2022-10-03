package com.android4you.space.presentation.rockets.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.R
import com.android4you.space.databinding.ItemRocketBinding
import com.android4you.space.domain.model.rockets.RocketModel

class RocketsViewHolder(private val binding: ItemRocketBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: RocketModel) {
        binding.apply {
            rockets = item
            executePendingBindings()
        }
        val bundle = Bundle()
        bundle.putParcelable("rocket", item)
        binding.rocketsCardview.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_homeFragment_to_rocketsFragment,
                bundle
            )
        )
    }

    companion object {
        fun from(parent: ViewGroup): RocketsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRocketBinding.inflate(layoutInflater, parent, false)
            return RocketsViewHolder(binding)
        }
    }
}
