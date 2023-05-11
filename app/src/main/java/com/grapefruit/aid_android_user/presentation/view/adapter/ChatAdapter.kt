package com.grapefruit.aid_android_user.presentation.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grapefruit.aid_android_user.data.dto.ChatData
import com.grapefruit.aid_android_user.databinding.ReciveMessageItemListBinding
import com.grapefruit.aid_android_user.databinding.SendMessageBinding

class ChatAdapter(val dataSet: List<ChatData>,val isUser: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val USER_MESSAGE = "user"
    private val AI_MESSAGE = "ai"

    inner class UserChatViewHolder(private val binding: SendMessageBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(data: ChatData){
                binding.sendMsg.text = data.message
                binding.time.text = data.time
            }
    }
    inner class AiChatViewHolder(private val binding: ReciveMessageItemListBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(data: ChatData){
            binding.reciveMsg.text = data.message
            binding.time.text = data.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        Log.d("testt_a",isUser[0].toString())
        return when (isUser) {
             USER_MESSAGE -> {
                val binding = SendMessageBinding.inflate(inflater, parent, false)
                UserChatViewHolder(binding)
            }
             AI_MESSAGE -> {
                val binding = ReciveMessageItemListBinding.inflate(inflater, parent, false)
                AiChatViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataSet = dataSet[position]
        when(isUser){
            USER_MESSAGE -> {
                val textHolder = holder as UserChatViewHolder
                textHolder.bind(dataSet)
            }
            AI_MESSAGE -> {
                val textHolder = holder as AiChatViewHolder
                textHolder.bind(dataSet)
            }
            else -> {

            }
        }
    }
}
