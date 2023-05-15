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
    private var _storeInfo = MutableLiveData<ShopDetailData>()

    val storeInfo: LiveData<ShopDetailData>
        get() = _storeInfo

    fun storeLoad(storeId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.searchStore(storeId)
            Log.d("response", response.code().toString())

            if (response.code() == 200) {
                _storeInfo.value = response.body()
                Log.d("testt_a", response.body().toString())
            } else {
                Log.d("testt_f", "fail")
            }
        }
    }
}