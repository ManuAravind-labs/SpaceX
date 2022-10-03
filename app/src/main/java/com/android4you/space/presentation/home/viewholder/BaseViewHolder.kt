package com.android4you.space.presentation.home.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<in T>(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context
    abstract fun bind(item: T)
}
