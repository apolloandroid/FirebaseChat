package com.example.myapplication.chat

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.remote.RemoteDataBase
import kotlinx.coroutines.*

class ChatViewModel(private val dataBase: RemoteDataBase, application: Application) : ViewModel() {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _onMessageSend = MutableLiveData<Boolean>()
    val onMessageSend: LiveData<Boolean>
        get() = _onMessageSend

    fun onSendButtonClickListener() {
        _onMessageSend.value = true
    }

    fun sendMessage(newMessageText: String) {
        viewModelScope.launch {
            dataBase.insert(newMessageText)
        }
        onMessageSent()
    }

    private fun onMessageSent() {
        _onMessageSend.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

