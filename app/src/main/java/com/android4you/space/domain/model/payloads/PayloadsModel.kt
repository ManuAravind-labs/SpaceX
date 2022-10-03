package com.android4you.space.domain.model.payloads

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PayloadsModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val reused: Boolean? = false,
    @ColumnInfo(name = "customers")
    val customers: List<String>?,
    @ColumnInfo(name = "nationalities")
    val nationalities: List<String>?,
    @ColumnInfo(name = "manufacturers")
    val manufacturers: List<String>?,
    val mass_kg: String? = "--",
    val lifespan_years: String? = "--",
    val orbit: String? = "--",
) : Parcelable
