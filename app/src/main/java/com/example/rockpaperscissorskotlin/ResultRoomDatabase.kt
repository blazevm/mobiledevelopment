package com.example.rockpaperscissorskotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Result::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ResultRoomDatabase : RoomDatabase() {

    abstract fun reminderDao(): ResultDao

    companion object {
        private const val DATABASE_NAME = "REMINDER_DATABASE"

        @Volatile
        private var resultRoomDatabaseInstance: ResultRoomDatabase? = null

        fun getDatabase(context: Context): ResultRoomDatabase? {
            if (resultRoomDatabaseInstance == null) {
                synchronized(ResultRoomDatabase::class.java) {
                    if (resultRoomDatabaseInstance == null) {
                        resultRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            ResultRoomDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return resultRoomDatabaseInstance
        }
    }

}