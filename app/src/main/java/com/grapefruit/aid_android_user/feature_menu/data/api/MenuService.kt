package com.grapefruit.aid_android_user.feature_menu.data.api

import com.grapefruit.aid_android_user.feature_menu.data.dto.CategoryDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.CheckMenuDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuService {
    @GET("menu/{storeId}")
    suspend fun menuList (
        @Path("storeId") storeId: Long
    ): Response<CheckMenuDTO>

    @GET("menu/detail/{menuId}")
    suspend fun menuDetail (
        @Path("menuId") menuId:Long
    ): Response<MenuDetailDTO>

    @GET("menu/category/{categoryId}")
    suspend fun menuCategory (
        @Path("categoryId") categoryId: Long
    ): Response<CategoryDTO>
}