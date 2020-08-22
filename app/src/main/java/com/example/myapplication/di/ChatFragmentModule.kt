package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.chat.ChatViewModel
import com.example.myapplication.chat.ChatViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ChatFragmentModule(private val context: Context) {
    @Provides
    fun provideChatViewModel(): ChatViewModel {
        val chatViewModelFactory = ChatViewModelFactory(context)
        return chatViewModelFactory.create(ChatViewModel::class.java)
    }
}