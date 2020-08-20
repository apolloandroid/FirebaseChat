package com.example.myapplication.di

import com.example.myapplication.chat.ChatFragment
import dagger.Component

@Component(modules = [ChatFragmentModule::class])
interface AppComponent {
    fun injectChatFragment(chatFragment: ChatFragment)
}