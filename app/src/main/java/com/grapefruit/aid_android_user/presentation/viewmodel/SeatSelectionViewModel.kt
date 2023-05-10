package com.grapefruit.aid_android_user.presentation.viewmodel

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.grapefruit.aid_android_user.presentation.view.SeatSelectionActivity
import com.grapefruit.aid_android_user.data.dto.SeatDTO
import com.grapefruit.aid_android_user.di.NetworkModule
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeatSelectionViewModel: ViewModel() {

    private val _seatListResponse = MutableLiveData<List<SeatDTO>>()
    val seatListResponse: LiveData<List<SeatDTO>> get() = _seatListResponse

    fun seatList(storeId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.seatList(storeId)
            Log.d("response", response.code().toString())

            if (response.code() == 200) {
                _seatListResponse.value = response.body()
                Log.d("testt_a", response.body().toString())
            } else {
                Log.d("testt_f", "fail")
            }
        }
    }

    fun allow(seatId: Long, activity: Activity){
        viewModelScope.launch{
            val response = NetworkModule.allow(seatId)

            when(response.code()){
                204 -> {
                    Log.d("자리선택 성공", "${response.code()}")
                }
                409 -> {
                    Log.d("자리 사용중", "${response.code()}")
                    Toast.makeText(activity,"이미 사용중인 자리입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun cancel(seatId: Long, activity: SeatSelectionActivity){
        viewModelScope.launch {
            val response = NetworkModule.cancel(seatId)

            when(response.code()){
                204 -> {
                    Log.d("자리취소 성공", "${response.code()}")
                }
                409 -> {
                    Log.d("자리 사용안하는중", "${response.code()}")
                    Toast.makeText(activity,"사용중이지 않은 좌석입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}