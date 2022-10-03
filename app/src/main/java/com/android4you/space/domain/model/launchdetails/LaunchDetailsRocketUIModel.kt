package com.android4you.space.domain.model.launchdetails

import com.android4you.space.domain.model.rockets.RocketModel

data class LaunchDetailsRocketUIModel(
    val title: String,
    val rocketModel: RocketModel
) : BaseDetailsUIModel