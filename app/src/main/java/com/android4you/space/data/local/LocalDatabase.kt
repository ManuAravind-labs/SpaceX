package com.android4you.space.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android4you.space.domain.model.crew.CrewModel
import com.android4you.space.domain.model.payloads.PayloadsModel

@Database(entities = [PayloadsModel::class, CrewModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}
