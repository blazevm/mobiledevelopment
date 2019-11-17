package com.example.rockpaperscissorskotlin

import androidx.room.*


@Dao
interface ResultDao {

    @Query("SELECT * FROM results")
    suspend fun getAllReminders(): List<Result>

    @Insert
    fun insertReminder(reminder: Result)

    @Delete
    suspend fun deleteReminder(reminder: Result)

    @Update
    suspend fun updateReminder(reminder: Result)

}