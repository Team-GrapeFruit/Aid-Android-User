package com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.vm


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grapefruit.aid_android_user.feature_qrcode_scan.data.Api.ApiBulider
import com.grapefruit.aid_android_user.feature_qrcode_scan.data.DTO.ShopDetail
import kotlinx.coroutines.launch


class QrcodeViewModel : ViewModel(){
    private var _storeInfo = MutableLiveData<ShopDetail>()

    val storeInfo: LiveData<ShopDetail>
        get() = _storeInfo

    fun storeLoad(storeId: String){
        viewModelScope.launch {
            val response = ApiBulider.searchStore(storeId)
            if(response.body() != null){
                _storeInfo.value = response.body()
                Log.d("testt",response.body().toString())
            }
        }
    }
}