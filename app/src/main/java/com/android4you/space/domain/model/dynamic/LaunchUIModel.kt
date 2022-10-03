package com.android4you.space.domain.model.dynamic

import com.android4you.space.domain.model.LaunchModel
import com.android4you.space.domain.model.ResponseType

data class LaunchUIModel(
    val title: String,
    val launches: List<LaunchModel>,
    val type: ResponseType
) : BaseUIModel
