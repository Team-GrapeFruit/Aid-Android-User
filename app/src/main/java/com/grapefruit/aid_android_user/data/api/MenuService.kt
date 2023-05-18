package com.grapefruit.aid_android_user.data.api

import com.grapefruit.aid_android_user.data.dto.CategoryData
import com.grapefruit.aid_android_user.data.dto.CheckMenuData
import com.grapefruit.aid_android_user.data.dto.MenuDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuService {
    @GET("menu/{storeId}")
    suspend fun menuList (
        @Path("storeId") storeId: Long
    ): Response<CheckMenuData>

    @GET("menu/detail/{menuId}")
    suspend fun menuDetail (
        @Path("menuId") menuId:Long
    ): Response<MenuDetailData>

    @GET("menu/category/{categoryId}")
    suspend fun menuCategory (
        @Path("categoryId") categoryId: Long
    ): Response<CategoryData>
}