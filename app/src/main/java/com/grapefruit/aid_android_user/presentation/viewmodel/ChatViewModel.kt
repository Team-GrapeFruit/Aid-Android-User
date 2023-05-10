package com.grapefruit.aid_android_user.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grapefruit.aid_android_user.data.dto.ChatData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatViewModel: ViewModel() {
    private var _chatInfo = MutableLiveData<ChatData>()
    private var chatCnt: Int = 0

    val ChatInfo: LiveData<ChatData>
        get() = _chatInfo

    fun sendMessage(message: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference().child("message")
        val time = getTime()
        val dataInput = ChatData(
            message = message,
            time = time,
            messageNum = (++chatCnt).toString()
        )
        myRef
            .child("user")
            .child("message" + chatCnt )
            .setValue(dataInput)
        Log.d("msg",chatCnt.toString())
        //getMessage()
    }
    fun getMessage(){
        val database = FirebaseDatabase.getInstance().getReference()
            .child("message")
            .child("ai")
            .child("answer"+chatCnt)

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.value != null) {
                    _chatInfo.value = snapshot.getValue(ChatData::class.java)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun getTime(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("a h:mm",Locale.KOREA)
        val formatted = current.format(formatter)

        Log.d("timee",formatted.toString())
        return formatted.toString()
    }
}