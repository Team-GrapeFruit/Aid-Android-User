package com.grapefruit.aid_android_user.data.api

import com.grapefruit.aid_android_user.data.dto.ShopDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreApi {
    @GET("store/{storeId}")
    suspend fun searchStore(@Path("storeId") storeId: Long): Response<ShopDetail>
}