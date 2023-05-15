package com.grapefruit.aid_android_user.di

import com.grapefruit.aid_android_user.data.api.SeatApi
import com.grapefruit.aid_android_user.data.api.StoreApi
import com.grapefruit.aid_android_user.data.dto.SeatDTO
import com.grapefruit.aid_android_user.data.dto.ShopDetailData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkModule {
    private const val BASE_URL = "https://port-0-aid-backend-user-nx562oley4rk2t.sel3.cloudtype.app/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val storeApi: StoreApi = retrofit.create(StoreApi::class.java)

    val seatApi: SeatApi = retrofit.create(SeatApi::class.java)

    suspend fun searchStore(storeId: Long): Response<ShopDetailData> {
        return storeApi.searchStore(storeId)
    }
    suspend fun seatList(storeId: Long): Response<List<SeatDTO>> {
        return seatApi.seatList(storeId)
    }

    suspend fun allow(seatId: Long): Response<Unit> {
        return seatApi.allow(seatId)
    }

    suspend fun cancel(seatId: Long): Response<Unit> {
        return seatApi.cancel(seatId)
    }
}