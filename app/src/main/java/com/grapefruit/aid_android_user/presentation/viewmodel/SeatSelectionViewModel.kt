package com.grapefruit.aid_android_user.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grapefruit.aid_android_user.presentation.view.SeatSelectionActivity
import com.grapefruit.aid_android_user.data.dto.SeatData
import com.grapefruit.aid_android_user.di.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeatSelectionViewModel: ViewModel() {

    private val _seatListResponse = MutableLiveData<List<SeatData>>()
    val seatListResponse: LiveData<List<SeatData>> get() = _seatListResponse

    fun seatListResponse(seatList: List<SeatData>){
        _seatListResponse.value = seatList
    }

    private val seatService = NetworkModule.seatApi

    fun seatList(storeId: Long, activity: SeatSelectionActivity) {
        seatService.seatList(storeId)
            .enqueue(object : Callback<List<SeatData>> {
                override fun onResponse(
                    call: Call<List<SeatData>>,
                    response: Response<List<SeatData>>
                ) {
                    if (response.isSuccessful){
                        val seatList = response.body()
                        Log.d("자리 가져오기 성공","$seatList")

                        val viewModel = ViewModelProvider(activity)[SeatSelectionViewModel::class.java]
                        seatList?.let {
                            viewModel.seatListResponse(seatList)
                        }
                    }
                }

                override fun onFailure(call: Call<List<SeatData>>, t: Throwable) {
                    Log.d("통신 실패",t.message.toString())
                }
            })
    }

    fun allow(seatId: Long, activity: SeatSelectionActivity){
        seatService.allow(seatId)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    val result = response.code()
                    when(result){
                        204 -> {
                            Log.d("자리선택 성공", "$result")
                        }
                        409 -> {
                            Log.d("자리 사용중", "$result")
                            Toast.makeText(activity,"이미 사용중인 자리입니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("통신 실패",t.message.toString())
                }
            })
    }

    fun cancel(seatId: Long, activity: SeatSelectionActivity){
        seatService.cancel(seatId)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    val result = response.code()
                    when(result){
                        204 -> {
                            Log.d("자리취소 성공", "$result")
                        }
                        409 -> {
                            Log.d("자리 사용안하는중", "$result")
                            Toast.makeText(activity,"사용중이지 않은 좌석입니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Log.d("통신 실패",t.message.toString())
                }
            })
    }
}