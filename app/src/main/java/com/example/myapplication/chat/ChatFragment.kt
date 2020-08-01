package com.example.myapplication.chat

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ChatFragmentBinding
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth


class ChatFragment : Fragment() {
    private val SIGN_IN_CODE = 1
    private lateinit var binding: ChatFragmentBinding
    private lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider.NewInstanceFactory().create(ChatViewModel::class.java)
        binding = ChatFragmentBinding.inflate(inflater)
        binding.apply {
            viewModel = viewModel
            buttonSend.setOnClickListener { onSendListener() }
            listMessages.adapter = MessageAdapter
        }

        viewModel.onMessageSend.observe(viewLifecycleOwner, Observer { done ->
            if (done) {
                binding.editMessageField.text.clear()
                viewModel.onFabClickDone()
//                binding.editMessageField.hideKeyboard()
                binding.listMessages.scrollToPosition(MessageAdapter.itemCount - 1)
            }
        })

//        user  authorised checking
        if (FirebaseAuth.getInstance().currentUser == null) {
            startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().build(), SIGN_IN_CODE
            )
        } else {
            Toast.makeText(context, "You authorised", Toast.LENGTH_LONG).show()
        }
        return binding.root
    }

    private fun onSendListener() {
        viewModel.onFabClick(binding.editMessageField.text.toString())
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}




