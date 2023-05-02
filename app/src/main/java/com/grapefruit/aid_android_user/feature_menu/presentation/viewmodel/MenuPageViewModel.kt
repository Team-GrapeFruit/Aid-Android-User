package com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDetailDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseSeatDTO
import com.grapefruit.aid_android_user.feature_menu.data.retrofit.RetrofitBuilder
import kotlinx.coroutines.launch

class MenuPageViewModel : ViewModel() {

    private val _menuListResponse = MutableLiveData<List<MenuDTO>>()
    private val _menuDetailResponse = MutableLiveData<MenuDetailDTO>()
    private val _purchaseListResponse = MutableLiveData<List<PurchaseDTO>>()
    private val _purchaseResponse = MutableLiveData<Unit>()

    val menuListResponse: LiveData<List<MenuDTO>>
        get() = _menuListResponse

    val menuDetailResponse: LiveData<MenuDetailDTO>
        get() = _menuDetailResponse

    val purchaseListResponse: LiveData<List<PurchaseDTO>>
        get() = _purchaseListResponse

    val purchaseResponse: LiveData<Unit>
        get() = _purchaseResponse

    fun menuListRoad(storeId: Long) {
        viewModelScope.launch {
            val response = RetrofitBuilder.menuList(storeId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _menuListResponse.value = response.body()
                Log.d("detail-in", menuListResponse.value.toString())
            }
        }
    }

    fun categoryMenuListRoad(categoryId: Long) {
        viewModelScope.launch {
            val response = RetrofitBuilder.categoryMenuList(categoryId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _menuListResponse.value = response.body()
                Log.d("detail-in", menuListResponse.value.toString())
            }
        }
    }

    fun getmenuList(): List<MenuDTO>? {
        return menuListResponse.value
    }

    fun menuDetailRoad(menuId: Long) {
        viewModelScope.launch {
            val response = RetrofitBuilder.menuDetail(menuId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _menuDetailResponse.value = response.body()
                Log.d("detail-in", menuDetailResponse.value.toString())
            }
        }
    }

    fun purchaseSeatRoad(seatId: Long, menuId: List<PurchaseSeatDTO>) {
        viewModelScope.launch {
            val response = RetrofitBuilder.purchaseSeat(seatId, menuId)
            Log.d("detail-in", response.body().toString())

            if(response.isSuccessful) {
                _purchaseResponse.value = response.body()
                Log.d("detail-in", purchaseResponse.value.toString())
            }
        }
    }

    fun deleteMenuRoad(purchaseId: Long) {
        viewModelScope.launch {
            val response = RetrofitBuilder.deleteMenu(purchaseId)

            if(response.isSuccessful) {
                _purchaseResponse.value = response.body()
            }
        }
    }

    fun quantityControlRoad(purchaseId: Long, quantity: Long) {
        viewModelScope.launch {
            val response = RetrofitBuilder.quantityControl(purchaseId, quantity)
            Log.d("detail-in", purchaseResponse.value.toString())

            if(response.isSuccessful) {
                _purchaseResponse.value = response.body()
                Log.d("detail-in",purchaseResponse.value.toString())
            }
        }
    }

    fun purchaseListRoad(seatId: Long) {
        viewModelScope.launch {
            val response = RetrofitBuilder.purchaseList(seatId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _purchaseListResponse.value = response.body()
                Log.d("detail-in",purchaseListResponse.value.toString())
            }
        }
    }
}