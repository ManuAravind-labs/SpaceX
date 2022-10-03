package com.android4you.space.domain.model.dynamic

import com.android4you.space.domain.model.ResponseType
import com.android4you.space.domain.model.crew.CrewModel

data class CrewUIModel(
    val title: String,
    val crewList: List<CrewModel>,
    val type: ResponseType
) : BaseUIModel
