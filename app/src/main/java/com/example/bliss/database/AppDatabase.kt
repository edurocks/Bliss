package com.example.bliss.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bliss.database.dao.BlissDao
import com.example.bliss.database.entity.EmojiEntity

@Database(entities = [EmojiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun blissDao() : BlissDao
}