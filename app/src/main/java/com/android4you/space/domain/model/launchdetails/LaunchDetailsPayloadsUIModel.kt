package com.android4you.space.domain.model.launchdetails

import com.android4you.space.domain.model.payloads.PayloadsModel

data class LaunchDetailsPayloadsUIModel(
    val title: String,
    val payloadsModel: List<PayloadsModel>
) : BaseDetailsUIModel
