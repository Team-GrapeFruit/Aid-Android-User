package com.grapefruit.aid_android_user.feature_seat.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grapefruit.aid_android_user.feature_seat.data.dto.SeatDTO

class SeatSelectionViewModel: ViewModel() {

    private val _seatListResponse = MutableLiveData<List<SeatDTO>>()
    val seatListResponse: LiveData<List<SeatDTO>> get() = _seatListResponse

    fun seatListResponse(seatList: List<SeatDTO>){
        _seatListResponse.value = seatList
    }
}