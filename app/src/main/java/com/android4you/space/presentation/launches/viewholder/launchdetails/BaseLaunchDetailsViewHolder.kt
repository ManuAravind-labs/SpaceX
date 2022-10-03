package com.android4you.space.presentation.launches.viewholder.launchdetails

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseLaunchDetailsViewHolder<in T>(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context
    abstract fun bind(item: T)
}
