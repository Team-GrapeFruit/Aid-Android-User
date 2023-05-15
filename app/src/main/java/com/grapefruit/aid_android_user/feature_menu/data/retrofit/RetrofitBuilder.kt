package com.grapefruit.aid_android_user.feature_menu.data.retrofit

import com.grapefruit.aid_android_user.feature_menu.data.api.MenuService
import com.grapefruit.aid_android_user.feature_menu.data.api.PurchaseService
import com.grapefruit.aid_android_user.feature_menu.data.dto.*
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://port-0-aid-backend-user-nx562oley4rk2t.sel3.cloudtype.app/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val menuPage: MenuService by lazy {
        retrofit.create(MenuService::class.java)
    }

    suspend fun menuList(storeId: Long): Response<CheckMenuDTO> {
        return menuPage.menuList(storeId)
    }

    suspend fun categoryMenuList(categoryId: Long): Response<CategoryDTO> {
        return menuPage.menuCategory(categoryId)
    }

    suspend fun menuDetail(menuId: Long): Response<MenuDetailDTO> {
        return menuPage.menuDetail(menuId)
    }

    val purchasePage: PurchaseService by lazy {
        retrofit.create(PurchaseService::class.java)
    }

    suspend fun orderMenuToPurchase(seatId: Long, body: List<PurchaseSeatDTO>): Response<Unit> {
        return purchasePage.orderMenu(seatId, body)
    }

    suspend fun quantityControl(purchaseId: Long, body: QuantityDTO): Response<Unit> {
        return purchasePage.quantityControl(purchaseId, body)
    }

    suspend fun deleteMenu(purchaseId: Long): Response<Unit> {
        return purchasePage.deleteMenu(purchaseId)
    }

    suspend fun purchaseList(seatId: Long): Response<List<PurchaseDTO>> {
        return purchasePage.addMenuList(seatId)
    }
}