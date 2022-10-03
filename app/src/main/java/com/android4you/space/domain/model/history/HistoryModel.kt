package com.android4you.space.domain.model.history

data class HistoryModel(
    val title: String,
    val event_date_utc: String,
    val event_date_unix: String,
    val details: String,
)
