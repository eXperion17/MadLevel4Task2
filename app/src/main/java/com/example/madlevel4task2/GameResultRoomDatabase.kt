package com.example.madlevel4task2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameResult::class], version = 1, exportSchema = false)
abstract class GameResultRoomDatabase : RoomDatabase() {

    abstract fun productDao(): GameResultDao

    companion object {
        private const val DATABASE_NAME = "GAME_RESULT_DATABASE"

        @Volatile
        private var gameResultRoomDatabaseInstance: GameResultRoomDatabase? = null

        fun getDatabase(context: Context): GameResultRoomDatabase? {
            if (gameResultRoomDatabaseInstance == null) {
                synchronized(GameResultRoomDatabase::class.java) {
                    if (gameResultRoomDatabaseInstance == null) {
                        gameResultRoomDatabaseInstance =
                            Room.databaseBuilder(context.applicationContext, GameResultRoomDatabase::class.java,
                                DATABASE_NAME
                            ).build()
                    }
                }
            }
            return gameResultRoomDatabaseInstance
        }
    }

}
