package com.grapefruit.aid_android_user.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grapefruit.aid_android_user.data.dto.*
import com.grapefruit.aid_android_user.di.NetworkModule
import kotlinx.coroutines.launch

class MenuPageViewModel : ViewModel() {
    private val _menuListResponse = MutableLiveData<CheckMenuDTO>()
    private val _menuByCategoryResponse = MutableLiveData<CategoryDTO>()
    private val _menuDetailResponse = MutableLiveData<MenuDetailDTO>()
    private val _purchaseListResponse = MutableLiveData<List<PurchaseDTO>>()
    private val _menuListReqDto = arrayListOf<PurchaseMenuDTO>()
    private val _menuInfoList = arrayListOf<PurchaseDTO>()

    val menuListResponse: LiveData<CheckMenuDTO>
        get() = _menuListResponse

    val menuByCategoryResponse: LiveData<CategoryDTO>
        get() = _menuByCategoryResponse

    val menuDetailResponse: LiveData<MenuDetailDTO>
        get() = _menuDetailResponse

    val purchaseListResponse: LiveData<List<PurchaseDTO>>
        get() = _purchaseListResponse

    val menuListReqDto: ArrayList<PurchaseMenuDTO>
        get() = _menuListReqDto

    val menuInfoList: ArrayList<PurchaseDTO>
        get() = _menuInfoList

    fun menuListRoad(storeId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.menuList(storeId)
            Log.d("detail-in-menulist", response.code().toString())

            if (response.code() == 200) {
                _menuListResponse.value = response.body()
                Log.d("detail-in-menulist", response.body().toString())
            }
        }
    }

    fun categoryMenuListRoad(categoryId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.categoryMenuList(categoryId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _menuByCategoryResponse.value = response.body()
                Log.d("detail-in", menuByCategoryResponse.value.toString())
            }
        }
    }

    fun menuDetailRoad(menuId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.menuDetail(menuId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _menuDetailResponse.value = response.body()
                Log.d("detail-in", menuDetailResponse.value.toString())
            }
        }
    }

    fun orderMenuToPurchaseRoad(seatId: Long, menuId: Long, quantity: Long) {
        viewModelScope.launch {
            /*val response =
                RetrofitBuilder.orderMenuToPurchase(seatId, )*/
        }
    }

    fun deleteMenuRoad(purchaseId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.deleteMenu(purchaseId)
            Log.d("detail-delete", response.body().toString())
        }
    }

    fun quantityControlRoad(purchaseId: Long, quantity: Long) {
        viewModelScope.launch {
            val response = NetworkModule.quantityControl(purchaseId, QuantityDTO(quantity))
            Log.d("ddd", response.code().toString())
            Log.d("testt", purchaseId.toString())
            Log.d("testt", quantity.toString())
        }
    }

    fun purchaseListRoad(seatId: Long) {
        viewModelScope.launch {
            val response = NetworkModule.purchaseList(seatId)
            Log.d("detail-in", response.body().toString())

            if (response.code() == 200) {
                _purchaseListResponse.value = response.body()
                Log.d("detail-in", purchaseListResponse.value.toString())
            }
        }
    }
}