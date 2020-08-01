package com.example.myapplication.chat

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.remote.RemoteDataBase

class ChatViewModelFactory(
    private val dataBase: RemoteDataBase,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(dataBase, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}