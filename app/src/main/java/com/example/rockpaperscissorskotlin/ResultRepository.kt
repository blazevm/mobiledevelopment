package com.example.rockpaperscissorskotlin

import android.content.Context

public class ResultRepository(context: Context) {

    private var resultDao: ResultDao

    init {
        val reminderRoomDatabase = ResultRoomDatabase.getDatabase(context)
        resultDao = reminderRoomDatabase!!.reminderDao()
    }

    suspend fun getAllReminders(): List<Result> {
        return resultDao.getAllReminders()
    }

    fun insertReminder(reminder: Result) {
        resultDao.insertReminder(reminder)
    }

    suspend fun deleteReminder(reminder: Result) {
        resultDao.deleteReminder(reminder)
    }

    suspend fun updateReminder(reminder: Result) {
        resultDao.updateReminder(reminder)
    }

}