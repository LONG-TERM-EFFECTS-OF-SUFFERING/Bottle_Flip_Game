package com.example.bottle_flip.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bottle_flip.model.challenge
import com.example.bottle_flip.repository.challengeRepository
import kotlinx.coroutines.launch


class ChallengeViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val challengeRepository = challengeRepository(context)


    private val _listChallenge = MutableLiveData<MutableList<challenge>>()
    val listInventory: LiveData<MutableList<challenge>> get() = _listChallenge

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    fun savechallenge(challenge: challenge) {
        viewModelScope.launch {

            _progresState.value = true
            try {
                challengeRepository.savechallenge(challenge)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun getListInventory() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listChallenge.value = challengeRepository.getListInventory()
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

