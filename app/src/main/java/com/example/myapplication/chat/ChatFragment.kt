package com.example.myapplication.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ChatFragmentBinding


class ChatFragment : Fragment() {

    private lateinit var binding: ChatFragmentBinding

    private lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        binding = ChatFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.listMessages.adapter = MessageAdapter

        viewModel.onMessageSend.observe(viewLifecycleOwner, Observer {
            if (it) sendMessage()
            else binding.editMessageField.text.clear()
        })

        return binding.root
    }

    private fun initViewModel() {
        val application = requireNotNull(this.activity).application
        viewModel = ChatViewModel(application.applicationContext)
    }

    private fun sendMessage() {
        viewModel.sendMessage(binding.editMessageField.text.toString())
    }
}




