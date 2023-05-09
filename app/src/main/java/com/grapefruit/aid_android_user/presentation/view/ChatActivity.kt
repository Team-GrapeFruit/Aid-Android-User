package com.grapefruit.aid_android_user.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.grapefruit.aid_android_user.databinding.ActivityChatBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            putMessage()
        }
    }
    fun putMessage() {       //메시지 전송
        val message = binding.editText.text.toString()
        if(message.isNotEmpty()){
            viewModel.sendMessage(message)
        }
    }
}