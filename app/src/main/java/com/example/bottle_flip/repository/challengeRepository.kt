package com.example.bottle_flip.repository
import android.content.Context
import android.util.Log
import com.example.bottle_flip.data.challengeDB
import com.example.bottle_flip.data.challengeDao
import com.example.bottle_flip.model.challenge
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class challengeRepository(val context: Context){
    private var challengeDao:challengeDao = challengeDB.getDatabase(context).challengeDao()

     suspend fun savechallenge(challenge:challenge){
         withContext(Dispatchers.IO){
             challengeDao.saveInventory(challenge)
             Log.d("repository", "Challenge saved: descripcion='${challenge.description}'")
         }
     }

    suspend fun getListChallenge():MutableList<challenge>{
        return withContext(Dispatchers.IO){
            challengeDao.getListchallenge()
        }
    }

    suspend fun deletechallenge(challenge: challenge){
        withContext(Dispatchers.IO){
            challengeDao.deletechallenge(challenge)
        }
    }

    suspend fun updateRepositoy(challenge: challenge){
        withContext(Dispatchers.IO){
            challengeDao.updatechallenge(challenge)
        }
    }


}