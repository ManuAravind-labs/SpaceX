package com.android4you.space.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.domain.model.payloads.PayloadsModel

@Dao
interface LocalDao {

    @Query("SELECT * FROM PayloadsModel WHERE id IN  (:ids)")
    suspend fun getPayloadsModelAll(ids: List<String>?): List<PayloadsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayloadsAll(userEntity: List<PayloadsModel>)

    @Query("SELECT * FROM CrewModel WHERE id IN  (:ids)")
    suspend fun getCrewModelAll(ids: List<String>?): List<CrewModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrewAll(userEntity: List<CrewModel>)
}
