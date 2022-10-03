package com.android4you.space.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.model.rockets.RocketModel
import com.android4you.space.presentation.launches.adapter.LaunchesAdapter
import com.android4you.space.presentation.rockets.adapter.RocketsAdapter

/**
 * Binding adapter used to to display workout time in the descriptions.
 */
@BindingAdapter("timeFormatted")
fun TextView.bindWorkTime(time: Long) {
    val toBind = "${time}sec"
    text = toBind
}

/**
 * Binding adapter used to submit list of workouts to the [WorkoutAdapter].
 */
@BindingAdapter("listLaunchData")
fun RecyclerView.bindLaunchRecyclerView(list: List<LaunchModel>?) {
    val adapter = adapter as LaunchesAdapter
    adapter.submitList(list)
}

@BindingAdapter("listRocketData")
fun RecyclerView.bindRocketRecyclerView(list: List<RocketModel>?) {
    val adapter = adapter as RocketsAdapter
    adapter.submitList(list)
}

