package com.android4you.space.domain.model.crew

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CrewModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val agency: String,
    val image: String,
    val wikipedia: String,
    @ColumnInfo(name = "launches")
    val launches: List<String>,
    val status: String
) : Parcelable
/***
 *
 *  "name": "Robert Behnken",
"agency": "NASA",
"image": "https://imgur.com/0smMgMH.png",
"wikipedia": "https://en.wikipedia.org/wiki/Robert_L._Behnken",
"launches": [
"5eb87d46ffd86e000604b388"
],
"status": "active",
"id": "5ebf1a6e23a9a60006e03a7a"
 *
 */
