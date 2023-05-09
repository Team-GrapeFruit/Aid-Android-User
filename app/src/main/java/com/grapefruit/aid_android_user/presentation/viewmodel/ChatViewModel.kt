package com.grapefruit.aid_android_user.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grapefruit.aid_android_user.data.dto.ChatData
import com.grapefruit.aid_android_user.data.dto.ShopDetailData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatViewModel: ViewModel() {
    private var _chatInfo = MutableLiveData<ShopDetailData>()

    val ChatInfo: LiveData<ShopDetailData>
        get() = _chatInfo

    fun sendMessage(message: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()
        val time = getTime()
        val dataInput = ChatData(
            msg = message,
            time = time
        )
        myRef.child("message").child("user").push().setValue(dataInput)
    }
    fun getTime(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("a h:mm",Locale.KOREA)
        val formatted = current.format(formatter)

        Log.d("timee",current.toString())
        return formatted.toString()
    }
}