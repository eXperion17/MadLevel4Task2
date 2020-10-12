package com.example.madlevel4task2

import android.content.Context

public class GameResultRepository(context: Context) {
    private var gameResultDao: GameResultDao;
    init {
        val gameResultRoomDatabase = GameResultRoomDatabase.getDatabase(context);
        gameResultDao = gameResultRoomDatabase!!.gameResultDao();
    }

    suspend fun getAllGameResults(): List<GameResult> {
        return gameResultDao.getAllGameResults();
    }

    suspend fun insertGameResult(reminder: GameResult) {
        gameResultDao.insertGameResult(reminder);
    }

    suspend fun deleteGameResults() {
        gameResultDao.deleteAllGameResults();
    }

    suspend fun updateReminder(reminder: GameResult) {
        gameResultDao.updateGameResult(reminder);
    }

}