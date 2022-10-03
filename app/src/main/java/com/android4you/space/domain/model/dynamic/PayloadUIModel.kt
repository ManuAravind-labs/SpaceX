package com.android4you.space.domain.model.dynamic

import com.android4you.space.domain.model.ResponseType
import com.android4you.space.domain.model.payloads.PayloadsModel

data class PayloadUIModel(
    val title: String,
    val payloads: List<PayloadsModel>,
    val type: ResponseType
) : BaseUIModel
