package com.grapefruit.aid_android_user.feature_qrcode_scan.data.Api

import com.grapefruit.aid_android_user.feature_qrcode_scan.data.DTO.ShopDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("store/{storeId}")
    suspend fun searchStore(@Path("storeId") storeId: String): Response<ShopDetail>
}