package com.grapefruit.aid_android_user.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grapefruit.aid_android_user.data.dto.ChatData
import com.grapefruit.aid_android_user.databinding.ActivityChatBinding
import com.grapefruit.aid_android_user.presentation.view.adapter.ChatAdapter
import com.grapefruit.aid_android_user.presentation.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private val viewModel by viewModels<ChatViewModel>()
    private var isUser: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            putMessage()
        }
        viewModel.isUser.observe(this){
            if(it.isNotEmpty()) {
                isUser = it
                Log.d("testt_a", isUser!!)
            }
        }
        viewModel.ChatInfo.observe(this){
            if(isUser != null && it != null) {
                Log.d("testt_c",it.toString())
                Log.d("testt_b", isUser!!)
                val chatList = ArrayList<ChatData>()
                chatList.add(it)

                val adapter = ChatAdapter(chatList, isUser!!)
                val recyclerView: RecyclerView = binding.chatView
                val layoutManager = LinearLayoutManager(this)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = adapter
            }
        }


    }
    fun putMessage() {       //메시지 전송
        val message = binding.editText.text.toString()
        if(message.isNotEmpty()){
            viewModel.sendMessage(message)

        }
    }

}