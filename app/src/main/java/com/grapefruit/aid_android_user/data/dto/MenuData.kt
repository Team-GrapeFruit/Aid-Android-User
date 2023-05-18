package com.grapefruit.aid_android_user.data.dto

import com.google.gson.annotations.SerializedName

data class MenuData(
    val menuId: Long,
    val menuName: String,
    val cost: Long,
    val menuImgURL: String?,
)

data class CheckMenuData(
    @SerializedName("singleGetMenuResDto")
    val singleMenuResponse: List<MenuData>
)

data class CategoryData(
    @SerializedName("getMenuByCategoryResDto")
    val categoryResponse: List<MenuData>
)