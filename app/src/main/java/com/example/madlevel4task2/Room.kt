package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

enum class GameMove {
    ROCK, PAPER, SCISSORS
}

enum class GameResult {
    WIN, LOSE, DRAW
}

@Entity(tableName = "room_table")
data class Room (
    @ColumnInfo(name = "date")
    var date:Date,

    @ColumnInfo(name = "computer")
    var computer:GameMove,

    @ColumnInfo(name = "player")
    var player:GameMove,

    @ColumnInfo(name = "result")
    var result:GameResult,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Long? = null
)