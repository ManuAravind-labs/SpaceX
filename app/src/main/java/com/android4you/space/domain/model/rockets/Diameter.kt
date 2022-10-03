package com.android4you.space.domain.model.rockets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Diameter(
    val feet: Double?,
    val meters: Double?
) : Parcelable
