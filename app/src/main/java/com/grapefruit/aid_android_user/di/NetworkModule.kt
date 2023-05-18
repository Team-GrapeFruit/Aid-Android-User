package com.grapefruit.aid_android_user.di

import com.grapefruit.aid_android_user.data.api.MenuService
import com.grapefruit.aid_android_user.data.api.PurchaseService
import com.grapefruit.aid_android_user.data.api.SeatApi
import com.grapefruit.aid_android_user.data.api.StoreApi
import com.grapefruit.aid_android_user.data.dto.*
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

    val seatApi: SeatApi = retrofit.create(SeatApi::class.java)

    val menuPage: MenuService = retrofit.create(MenuService::class.java)

    val purchasePage: PurchaseService = retrofit.create(PurchaseService::class.java)

    suspend fun menuList(storeId: Long): Response<CheckMenuData> {
        return menuPage.menuList(storeId)
    }

    suspend fun categoryMenuList(categoryId: Long): Response<CategoryData> {
        return menuPage.menuCategory(categoryId)
    }

    suspend fun menuDetail(menuId: Long): Response<MenuDetailData> {
        return menuPage.menuDetail(menuId)
    }

    suspend fun orderMenuToPurchase(seatId: Long, body: List<PurchaseSeatData>): Response<Unit> {
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

    suspend fun searchStore(storeId: Long): Response<ShopDetailData> {
        return storeApi.searchStore(storeId)
    }
    suspend fun seatList(storeId: Long): Response<List<SeatData>> {
        return seatApi.seatList(storeId)
    }

    suspend fun allow(seatId: Long): Response<Unit> {
        return seatApi.allow(seatId)
    }

    suspend fun cancel(seatId: Long): Response<Unit> {
        return seatApi.cancel(seatId)
    }
}