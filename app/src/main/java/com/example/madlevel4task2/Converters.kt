package com.example.madlevel4task2

import androidx.room.TypeConverter
import java.util.*

class Converters {

    // Dates
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long? {
        return date?.time?.toLong()
    }

    // Enums (GameMove)

    @TypeConverter
    fun stringToGameMove (value: String) : GameMove {
        return GameMove.valueOf(value);
    }

    @TypeConverter
    fun gameMoveToString (move: GameMove?) : String {
        return move.toString()
    }

    // Enums (GameResult)

    @TypeConverter
    fun stringToGameState (value: String) : GameState {
        return GameState.valueOf(value);
    }

    @TypeConverter
    fun gameStateToString (result: GameState?) : String {
        return result.toString()
    }

}