package com.grapefruit.aid_android_user.feature_seat.presentation.model

import com.grapefruit.aid_android_user.feature_seat.data.api.SeatService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://port-0-aid-backend-user-nx562oley4rk2t.sel3.cloudtype.app/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val seatService: SeatService by lazy {
        retrofit.create(SeatService::class.java)
    }
}