package com.grapefruit.aid_android_user.presentation.view


import com.grapefruit.aid_android_user.presentation.view.adapter.ChatAdapter
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grapefruit.aid_android_user.data.dto.ChatData
import com.grapefruit.aid_android_user.databinding.ActivityChatBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var adapter: ChatAdapter
    private val viewModel by viewModels<ChatViewModel>()
    private var isUser: String? = null
    val chatList: MutableList<ChatData> = mutableListOf()
    val isUserList: MutableList<String> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildAdapter()

        binding.btnSend.setOnClickListener {
            putMessage()
        }

        viewModel.isUser.observe(this){
            if(it.isNotEmpty()) {
                isUser = it
            }
        }

        viewModel.chatInfo.observe(this){
            if(it != null) {
                chatList.add(it)
                isUserList.add(isUser!!)
                Log.d("Activity_chatList",chatList.toString())
                Log.d("Activity_isUser", isUserList.toString())
                adapter.notifyDataSetChanged()
                binding.chatView.scrollToPosition(adapter.itemCount-1)
            }
        }

    }
    private fun putMessage() {       //메시지 전송
        val message = binding.editText.text.toString()
        if(message.isNotEmpty()){
            viewModel.sendMessage(message)
            val newItemPosition = chatList.size + 1  // 새로운 아이템의 위치
            adapter.notifyItemInserted(newItemPosition)
            binding.editText.text.clear()
        }
    }
    private fun buildAdapter(){
        adapter = ChatAdapter(chatList, isUserList)
        val recyclerView: RecyclerView = binding.chatView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }

}