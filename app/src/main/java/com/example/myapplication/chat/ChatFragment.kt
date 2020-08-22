package com.example.myapplication.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myapplication.databinding.ChatFragmentBinding
import com.example.myapplication.di.ChatFragmentModule
import com.example.myapplication.di.DaggerAppComponent
import javax.inject.Inject


class ChatFragment : Fragment() {

    private lateinit var binding: ChatFragmentBinding

    @Inject
     lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectFragment()
        binding = ChatFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.listMessages.adapter = MessageAdapter

        viewModel.onMessageSend.observe(viewLifecycleOwner, Observer {
            if (it) sendMessage()
            else binding.editMessageField.text.clear()
        })

        return binding.root
    }

    private fun injectFragment() {
        val component =
            DaggerAppComponent.builder().chatFragmentModule(ChatFragmentModule(context ?: return))
                .build()
        component?.injectChatViewModel(this)
    }

    private fun sendMessage() {
        viewModel.sendMessage(binding.editMessageField.text.toString())
    }
}



