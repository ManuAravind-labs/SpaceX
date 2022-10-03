package com.android4you.space.domain.model.rockets

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayloadWeight(
    val id: String?,
    val kg: Int?,
    val lb: Int?,
    val name: String?
) : Parcelable
