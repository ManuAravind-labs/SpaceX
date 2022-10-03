package com.android4you.space.domain.model.rockets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RocketModel(
//    val active: Boolean,
//    val boosters: Int,
    val company: String?,
    val cost_per_launch: Int?,
    val country: String?,
    val diameter: Diameter?,
//    val engines: Engines,
//    val first_flight: String,
//    val first_stage: FirstStage,
    val flickr_images: List<String>?,
    val height: Height?,
    val id: String,
//    val landing_legs: LandingLegs,
    val mass: Mass?,
    val name: String?,
    val description: String?,
    val payload_weights: List<PayloadWeight>?,
//    val second_stage: SecondStage,
//    val stages: Int,
//    val success_rate_pct: Int,
//    val type: String,
    val wikipedia: String
) : Parcelable
