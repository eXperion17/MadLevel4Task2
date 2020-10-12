package com.example.madlevel4task2

import androidx.room.*

@Dao
interface GameResultDao {

    @Query("SELECT * FROM gameResult_table")
    suspend fun getAllGameResults(): List<GameResult>

    @Insert
    suspend fun insertGameResult(result: GameResult)

    @Delete
    suspend fun deleteGameResult(result: GameResult)

    @Query("DELETE FROM gameResult_table")
    suspend fun deleteAllGameResults()

    @Update
    suspend fun updateGameResult(result:GameResult)

    @Query( "SELECT * FROM gameResult_table order by Date limit 1")
    suspend fun getRecentGameResult() : GameResult

}
