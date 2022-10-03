package com.android4you.space.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LaunchModel(
    val flight_number: Int?,
    val name: String?,
    val links: LinkModel?,
    val details: String?,
    val rocket: String?,
    val success: Boolean?,
    val launchpad: String?,
    val crew: List<String>?,
    val ships: List<String>?,
    val capsules: List<String>?,
    val payloads: List<String>?,
    val date_utc: String?,
    val date_unix: String?,
    val date_local: String?,
) : Parcelable

@Parcelize
data class LinkModel(
    val patch: Patch?,
    val flickr: Flickr?
) : Parcelable

@Parcelize
data class Patch(
    val small: String?,
    val large: String?
) : Parcelable

@Parcelize
data class Flickr(
    val original: List<String?> = emptyList()
) : Parcelable
