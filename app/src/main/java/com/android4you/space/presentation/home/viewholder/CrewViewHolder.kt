package com.android4you.space.presentation.home.viewholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.R
import com.android4you.space.databinding.ItemListCrewBinding
import com.android4you.space.domain.model.crew.CrewModel

class CrewViewHolder(private val binding: ItemListCrewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CrewModel) {
        binding.apply {
            crews = item
            executePendingBindings()
        }
        val bundle = Bundle()
        bundle.putParcelable("crew", item)
        binding.crewCardView.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_homeFragment_to_crewDetailsFragment,
                bundle
            )
        )
    }

    companion object {
        fun from(parent: ViewGroup): CrewViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListCrewBinding.inflate(layoutInflater, parent, false)
            return CrewViewHolder(binding)
        }
    }
}
