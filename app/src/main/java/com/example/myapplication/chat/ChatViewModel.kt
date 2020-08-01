package com.example.myapplication.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.remote.RemoteDataBase
import kotlinx.coroutines.*

class ChatViewModel : ViewModel() {
    private val _onMessageSend = MutableLiveData<Boolean>()
    val onMessageSend: LiveData<Boolean>
        get() = _onMessageSend

    private val messageDataBase =
        RemoteDataBase
    private val viewModelJob = Job()
    private val scope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun onFabClick(newMessageText: String) {
        scope.launch {
            sendMessage(newMessageText)
        }
        _onMessageSend.value = true
    }

    private suspend fun sendMessage(newMessageText: String) {
        withContext(Dispatchers.Main) {
            messageDataBase.insert(newMessageText)
        }
    }

    fun onFabClickDone() {
        _onMessageSend.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

