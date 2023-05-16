package com.grapefruit.aid_android_user.data.dto

import com.google.gson.annotations.SerializedName

data class MenuDTO(
    val menuId: Long,
    val menuName: String,
    val cost: Long,
    val menuImgURL: String?,
)

data class CheckMenuDTO(
    @SerializedName("singleGetMenuResDto")
    val singleMenuResponse: List<MenuDTO>
)

data class CategoryDTO(
    @SerializedName("getMenuByCategoryResDto")
    val categoryResponse: List<MenuDTO>
)