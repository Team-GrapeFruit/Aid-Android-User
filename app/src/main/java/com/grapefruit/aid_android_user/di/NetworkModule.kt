package com.grapefruit.aid_android_user.di

import com.grapefruit.aid_android_user.data.api.SeatApi
import com.grapefruit.aid_android_user.data.api.StoreApi
import com.grapefruit.aid_android_user.data.dto.ShopDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://port-0-aid-backend-user-nx562oley4rk2t.sel3.cloudtype.app/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val storeApi: StoreApi = retrofit.create(StoreApi::class.java)

    suspend fun searchStore(storeId: String): Response<ShopDetail> {
        return storeApi.searchStore(storeId)
    }
    val seatApi: SeatApi by lazy {
        retrofit.create(SeatApi::class.java)
    }
}