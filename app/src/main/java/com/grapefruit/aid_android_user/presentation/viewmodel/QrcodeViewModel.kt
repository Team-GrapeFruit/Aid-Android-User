package com.grapefruit.aid_android_user.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grapefruit.aid_android_user.di.NetworkModule
import com.grapefruit.aid_android_user.data.dto.ShopDetailData
import kotlinx.coroutines.launch


class QrcodeViewModel : ViewModel() {
    private var _isSuccess = MutableLiveData<Boolean>()

    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    fun storeLoad(storeId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.searchStore(storeId)
            Log.d("viewModel_response", response.code().toString())

            if (response.code() == 200) {
                _isSuccess.value = true
                Log.d("viewModel_success", response.body().toString())
            } else {
                _isSuccess.value = false
                Log.d("viewModel_fail", "fail")
            }
        }
    }
}