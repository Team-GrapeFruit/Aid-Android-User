package com.grapefruit.aid_android_user.presentation.viewmodel

import android.util.Log
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

    fun firestMessage(){
        val time = getTime()
        val dataInput = ChatData(
            message = "안녕하세요 AID입니다, AID는 다양한 주제에 대해 대화하고 도움을 줄수 있는 인공지능 입니다." +
                    "아래는 몇가지 도움을 드릴수 있는 명령어입니다." +
                    "\n1. !메뉴 : 채팅 페이지를 종료하고 메뉴 화면으로 이동합니다." +
                    "\n2. !종료 : 앱을 종료합니다." +
                    "\n위의 명령어 외에도 다양한 주제에 대해 대화하거나 질문을 하실 수 있습니다. 어떤 것을 도와드릴까요?",
            time = time
        )
        _isUser.value = "ai"
        _chatInfo.value = dataInput
    }
    fun sendMessage(message: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference().child("message")
        val time = getTime()

        val dataInput = ChatData(
            message = message,
            time = time,
            messageNum = (++chatCnt).toString()
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
                    Log.d("testt",snapshot.value.toString())
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