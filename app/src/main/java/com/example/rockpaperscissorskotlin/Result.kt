package com.example.rockpaperscissorskotlin

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.android.parcel.Parcelize
import java.util.*

enum class Move(val order: Int) {
    ROCK(0),
    PAPER(1),
    SCISSORS(2);
}

enum class Win(val order: Int) {
    WIN(0),
    LOSE(1),
    DRAW(2);
}

@Entity(tableName = "results")
data class Result(
    @ColumnInfo(name = "computerAnswer")
    var computerAnswer: Move,
    @ColumnInfo(name = "playerAnswer")
    var playerAnswer: Move,
    @ColumnInfo(name = "messageWinner")
    var messageWinner: Win,
    @ColumnInfo(name = "date")
    var date: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromIntToWin(value: Int?): Win? {
        return value?.let { Win.values()[it] }
    }

    @TypeConverter
    fun winToInt(date: Win?): Int? {
        return date?.order
    }

    @TypeConverter
    fun fromIntToMove(value: Int?): Move? {
        return value?.let { Move.values()[it] }
    }

    @TypeConverter
    fun moveToInt(date: Move?): Int? {
        return date?.order
    }

}
