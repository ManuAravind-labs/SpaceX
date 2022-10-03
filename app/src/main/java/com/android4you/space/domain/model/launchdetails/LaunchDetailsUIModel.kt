package com.android4you.space.domain.model.launchdetails

import com.android4you.space.domain.model.LaunchModel

data class LaunchDetailsUIModel(
    val title: String,
    val launchModel: LaunchModel
) : BaseDetailsUIModel

