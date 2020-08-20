package com.example.myapplication.di

import com.example.myapplication.chat.ChatFragment
import com.example.myapplication.chat.ChatViewModel
import com.example.myapplication.chat.ChatViewModelFactory
import com.example.myapplication.repository.remote.RemoteDataBase
import dagger.Module
import dagger.Provides

@Module
class ChatFragmentModule(private val fragment:ChatFragment) {

    @Provides
    fun provideChatViewModel(): ChatViewModel? {
        return fragment.context?.let { ChatViewModelFactory(it).create(ChatViewModel::class.java) }
    }

//    @Provides
//    fun provideChatViewModelFactory()
}