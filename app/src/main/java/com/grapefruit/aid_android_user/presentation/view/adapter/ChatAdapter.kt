package com.grapefruit.aid_android_user.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grapefruit.aid_android_user.data.dto.ChatData
import com.grapefruit.aid_android_user.databinding.ReciveMessageItemListBinding
import com.grapefruit.aid_android_user.databinding.SendMessageBinding

class ChatAdapter(private val dataSet: MutableList<ChatData>, private val isUserList: MutableList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class UserChatViewHolder(private val binding: SendMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatData) {
            binding.sendMsg.text = data.message
            binding.time.text = data.time
        }
    }

    inner class AiChatViewHolder(private val binding: ReciveMessageItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatData) {
            if(data.message == "1"){
                binding.reciveMsg.text = "메뉴 선택 화면으로 이동합니다."
            }else if(data.message == "2"){
                binding.reciveMsg.text = "이용해 주셔서 감사합니다."
            }else{
                binding.reciveMsg.text = data.message
            }
            binding.time.text = data.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d("Adapter_viewType",viewType.toString())
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val binding = SendMessageBinding.inflate(inflater, parent, false)
                UserChatViewHolder(binding)
            }
            VIEW_TYPE_AI -> {
                val binding = ReciveMessageItemListBinding.inflate(inflater, parent, false)
                AiChatViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        Log.d("Adapter_dataSet.size",dataSet.size.toString())
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = dataSet[position]
        Log.d("Adapter_holder", holder.itemViewType.toString())
        Log.d("Adapter_holder",holder.toString())
        Log.d("Adapter_data",data.toString())
        when (holder.itemViewType) {
            VIEW_TYPE_USER -> {
                val userChatHolder = holder as UserChatViewHolder
                userChatHolder.bind(data)
            }
            VIEW_TYPE_AI -> {
                val aiChatHolder = holder as AiChatViewHolder
                aiChatHolder.bind(data)
            }
            else ->{}
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("Adapter_dataSet",dataSet.toString())
        Log.d("Adapter_isUser",isUserList.toString())
        Log.d("Adapter_position",position.toString())
        if (position < 0 || position >= dataSet.size || position >= isUserList.size) {
            return VIEW_TYPE_NULL
        }
        val user = isUserList[position]
        return when (user) {
            USER_MESSAGE -> VIEW_TYPE_USER
            AI_MESSAGE -> VIEW_TYPE_AI
            else -> VIEW_TYPE_NULL
        }
    }

    companion object {
        private const val VIEW_TYPE_NULL = -1
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_AI = 2

        private const val USER_MESSAGE = "user"
        private const val AI_MESSAGE = "ai"
    }
}
