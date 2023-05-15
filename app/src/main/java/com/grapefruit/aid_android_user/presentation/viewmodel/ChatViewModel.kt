package com.grapefruit.aid_android_user.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.grapefruit.aid_android_user.data.dto.ChatData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatViewModel: ViewModel() {
    private var _chatInfo = MutableLiveData<ChatData>()
    private var _isUser = MutableLiveData<String>()
    private var chatCnt: Int = 0

    val chatInfo: LiveData<ChatData>
        get() = _chatInfo
    val isUser: LiveData<String>
        get() = _isUser

    fun sendMessage(message: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference().child("message")
        val time = getTime()

        val dataInput = ChatData(
            message = message,
            time = time,
            messageNum = (++chatCnt).toString(),
        )

        _isUser.value = "user"
        _chatInfo.value = dataInput

        myRef
            .child("user")
            .child("message" + chatCnt )
            .setValue(dataInput)

        getMessage()
    }
    fun getMessage() {
        val database = FirebaseDatabase.getInstance().getReference()
            .child("message")
            .child("ai")
        val query: Query = database.child("answer" + chatCnt)

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value != null) {
                    _isUser.value = "ai"
                    _chatInfo.value = snapshot.getValue(ChatData::class.java)
                    query.removeEventListener(this)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        }

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                query.addValueEventListener(valueEventListener)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun getTime(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("a h:mm",Locale.KOREA)
        val formatted = current.format(formatter)

        return formatted.toString()
    }
}