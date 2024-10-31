package com.example.bottle_flip.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bottle_flip.model.challenge

@Dao
interface challengeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInventory(challenge: challenge)

    @Query("SELECT * FROM challenge")
    suspend fun getListchallenge(): MutableList<challenge>

    @Delete
    suspend fun deletechallenge(challenge: challenge)

    @Update
    suspend fun updatechallenge(challenge: challenge)
}