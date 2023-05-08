package com.grapefruit.aid_android_user.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.grapefruit.aid_android_user.data.dto.ShopDetailData

class ChatViewModel: ViewModel() {
    private var _chatInfo = MutableLiveData<ShopDetailData>()

    val ChatInfo: LiveData<ShopDetailData>
        get() = _chatInfo

    fun sendMsg(msg: String){
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue(msg)
    }

}