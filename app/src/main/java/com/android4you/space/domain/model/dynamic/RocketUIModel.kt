package com.android4you.space.domain.model.dynamic

import com.android4you.space.domain.model.ResponseType
import com.android4you.space.domain.model.launchdetails.BaseDetailsUIModel
import com.android4you.space.domain.model.rockets.RocketModel

data class RocketUIModel(
    val title: String,
    val rockets: List<RocketModel>,
    val type: ResponseType
) : BaseUIModel
