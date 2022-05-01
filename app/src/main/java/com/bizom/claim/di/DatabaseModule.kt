package com.bizom.claim.di

import android.content.Context
import androidx.room.Room
import com.bizom.claim.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class DatabaseModule {

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }

    companion object {

        private const val DATABASE_NAME = "claim-db"
    }
}