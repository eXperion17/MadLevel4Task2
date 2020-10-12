package com.example.madlevel4task2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameResultDao {

    @Query("SELECT * FROM gameResult_table")
    suspend fun getAllProducts(): List<GameResult>

    @Insert
    suspend fun insertProduct(product: GameResult)

    @Delete
    suspend fun deleteProduct(product: GameResult)

    @Query("DELETE FROM gameResult_table")
    suspend fun deleteAllProducts()

}
