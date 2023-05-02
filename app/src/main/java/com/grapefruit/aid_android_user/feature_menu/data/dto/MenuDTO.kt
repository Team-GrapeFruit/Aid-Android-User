package com.grapefruit.aid_android_user.feature_menu.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


data class MenuDTO(
    @SerializedName("menu")
    val menu: List<MenuModel>
) {
    data class MenuModel(
        val menuId: Long,
        val menuName: String,
        val cost: Long,
        val menuImgURL: String?
    )
}