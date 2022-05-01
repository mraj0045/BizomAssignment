package com.bizom.claim.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Room database implementation.
 *
 */
@Database(entities = [ClaimData::class], version = 1)
@TypeConverters(value = [MapTypeConverter::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun claimDao(): ClaimDao

}
