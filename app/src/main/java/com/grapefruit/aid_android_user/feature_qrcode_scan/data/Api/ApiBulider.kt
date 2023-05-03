package com.grapefruit.aid_android_user.feature_qrcode_scan.data.Api

import com.grapefruit.aid_android_user.feature_qrcode_scan.data.DTO.ShopDetail
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBulider {
    private const val BASE_URL = "https://port-0-aid-backend-user-nx562oley4rk2t.sel3.cloudtype.app/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: Api = retrofit.create(Api::class.java)

    suspend fun searchStore(storeId: String): Response<ShopDetail> {
        return service.searchStore(storeId)
    }
}