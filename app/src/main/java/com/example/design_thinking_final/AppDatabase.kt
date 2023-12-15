package com.example.design_thinking_final

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Entity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): DAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null)
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "data.db"
                    )
                        .build()
                }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}