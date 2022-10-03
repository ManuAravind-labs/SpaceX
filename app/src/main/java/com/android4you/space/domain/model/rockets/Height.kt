package com.android4you.space.domain.model.rockets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Height(
    val feet: Double? = 0.00,
    val meters: String?
) : Parcelable
