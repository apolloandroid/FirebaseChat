package com.example.myapplication.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ChatFragmentBinding
import com.example.myapplication.repository.remote.RemoteDataBase


class ChatFragment : Fragment() {

    private lateinit var binding: ChatFragmentBinding
    private val viewModel: ChatViewModel by lazy { initViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChatFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.listMessages.adapter = MessageAdapter

        viewModel.onMessageSend.observe(viewLifecycleOwner, Observer {
            if (it) sendMessage()
            else binding.editMessageField.text.clear()
        })

        return binding.root
    }

    private fun initViewModel(): ChatViewModel {
        val application = requireNotNull(activity).application
        val chatViewModelFactory = ChatViewModelFactory(RemoteDataBase, application)
        return chatViewModelFactory.create(ChatViewModel::class.java)
    }

    private fun sendMessage() {
        viewModel.sendMessage(binding.editMessageField.text.toString())
    }
}




