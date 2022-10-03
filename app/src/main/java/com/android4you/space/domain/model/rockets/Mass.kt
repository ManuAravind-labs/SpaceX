package com.android4you.space.domain.model.rockets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mass(
    val kg: Int?,
    val lb: Int?
) : Parcelable
