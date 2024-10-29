package com.example.bottle_flip.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bottle_flip.model.challenge
import com.example.bottle_flip.utils.Constants.NAME_BD


@Database(entities = [challenge::class], version = 1)
abstract class challengeDB : RoomDatabase() {

    abstract fun challengeDao(): challengeDao

    companion object{
        fun getDatabase(context: Context): challengeDB {
            return Room.databaseBuilder(
                context.applicationContext,
                challengeDB::class.java,
                NAME_BD
            ).build()
        }
    }
}