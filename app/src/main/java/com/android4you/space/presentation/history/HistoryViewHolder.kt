package com.android4you.space.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.databinding.LayoutHistoryBinding
import com.android4you.space.domain.model.history.HistoryModel

class HistoryViewHolder(private val binding: LayoutHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HistoryModel) {
        binding.apply {
            histories = item
            executePendingBindings()
        }
    }

    companion object {
        fun from(parent: ViewGroup): HistoryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutHistoryBinding.inflate(layoutInflater, parent, false)
            return HistoryViewHolder(binding)
        }
    }
}
