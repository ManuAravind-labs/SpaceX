package com.android4you.space.domain.model.pads

data class LandPadsModel(
    val id: String,
    val name: String,
    val full_name: String,
    val type: String,
    val locality: String,
    val region: String,
    val latitude: Double,
    val longitude: Double,
    val landing_attempts: Int,
    val landing_successes: Int,
    val details: String,
    val launches: List<String>
)
