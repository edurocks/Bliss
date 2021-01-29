package com.example.bliss.di

import android.content.Context
import androidx.room.Room
import com.example.bliss.database.AppDatabase
import com.example.bliss.database.dao.BlissDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context) : AppDatabase {
        return Room.databaseBuilder(applicationContext,
            AppDatabase::class.java,
            "bliss")
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) : BlissDao{
        return appDatabase.blissDao()
    }
}