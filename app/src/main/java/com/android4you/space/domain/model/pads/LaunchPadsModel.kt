package com.android4you.space.domain.model.pads

data class LaunchPadsModel(
    val id: String,
    val name: String?,
    val full_name: String?,
    val timezone: String?,
    val locality: String?,
    val region: String?,
    val latitude: Double,
    val longitude: Double,
    val launch_attempts: Int? = 0,
    val launch_successes: Int? = 0,
    val status: String,
    val launches: List<String>,
    val rockets: List<String>
)
