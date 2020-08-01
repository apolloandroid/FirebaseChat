package com.example.myapplication.repository.remote

import com.example.myapplication.repository.model.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object RemoteDataBase {

    suspend fun insert(newMessage: String) {
        FirebaseDatabase.getInstance().reference.push().setValue(
            Message(
                FirebaseAuth.getInstance().currentUser?.email!!,
                newMessage
            )
        )
    }
}