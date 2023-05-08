package com.grapefruit.aid_android_user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.grapefruit.aid_android_user.data.dto.ShopDetailData

class ChatViewModel: ViewModel() {
    private var _chatInfo = MutableLiveData<ShopDetailData>()

    val ChatInfo: LiveData<ShopDetailData>
        get() = _chatInfo

    fun sendMsg(msg: String){
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()
        myRef.child("message").push().setValue(msg)
    }
}