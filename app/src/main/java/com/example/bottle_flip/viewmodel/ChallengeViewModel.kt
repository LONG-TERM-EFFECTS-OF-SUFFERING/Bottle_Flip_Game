package com.example.bottle_flip.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bottle_flip.model.challenge
import com.example.bottle_flip.data.challengeDB
import com.example.bottle_flip.repository.challengeRepository
import kotlinx.coroutines.launch
import com.example.bottle_flip.data.challengeDao


class ChallengeViewModel(application: Application) : AndroidViewModel(application) {

    val context = getApplication<Application>()
    private val challengeRepository = challengeRepository(context)


    private val _listChallenge = MutableLiveData<MutableList<challenge>>()
    val listChallenge: LiveData<MutableList<challenge>> get() = _listChallenge

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    fun savechallenge(challenge: challenge) {
        viewModelScope.launch {
            Log.d("viewModel","ViewModel")
            _progresState.value = true
            try {
                challengeRepository.savechallenge(challenge)
                Log.d("viewModel","ViewModel")
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun getListChallenge() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listChallenge.value = challengeRepository.getListChallenge()
                Log.d("ChallengeViewModel", "List of challenges: ${_listChallenge.value}")
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }

        }
    }

    fun challengeInventory(challenge: challenge) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                challengeRepository.deletechallenge(challenge)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }

        }
    }

    fun updateInventory(challenge: challenge) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                challengeRepository.updateRepositoy(challenge)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

}

