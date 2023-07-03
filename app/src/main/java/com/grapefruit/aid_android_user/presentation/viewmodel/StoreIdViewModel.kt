package com.grapefruit.aid_android_user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grapefruit.aid_android_user.di.NetworkModule
import com.grapefruit.aid_android_user.presentation.view.QrScanActivity
import kotlinx.coroutines.launch

class StoreIdViewModel: ViewModel() {
    private var _storeId = MutableLiveData<Long>()

    val storeId: LiveData<Long>
        get() = _storeId

    fun storeIdRoad(storeId: Long) {
        _storeId.value = storeId
        Log.d("storeId", _storeId.value.toString())
    }
}