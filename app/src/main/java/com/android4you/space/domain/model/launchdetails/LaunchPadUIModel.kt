package com.android4you.space.domain.model.launchdetails

import com.android4you.space.domain.model.pads.LaunchPadsModel

data class LaunchPadUIModel(
    val title: String,
    val padsModel: LaunchPadsModel
) : BaseDetailsUIModel