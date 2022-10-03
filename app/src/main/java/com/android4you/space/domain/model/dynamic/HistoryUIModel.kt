package com.android4you.space.domain.model.dynamic

import com.android4you.space.domain.model.ResponseType
import com.android4you.space.domain.model.history.HistoryModel

data class HistoryUIModel(
    val title: String,
    val histories: List<HistoryModel>,
    val type: ResponseType
) : BaseUIModel
