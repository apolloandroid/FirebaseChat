package com.example.myapplication.repository.model

import java.util.Date


data class Message(
    var userName: String = "",
    var textMessage: String = "",
    var messageTime: Long = Date().time
)