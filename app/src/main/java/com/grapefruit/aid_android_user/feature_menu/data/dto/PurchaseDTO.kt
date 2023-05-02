package com.grapefruit.aid_android_user.feature_menu.data.dto


data class PurchaseDTO(
    val purchaseId: Long,
    val quantity: Long,
    val purchaseMenu: MenuListDTO
) {
    data class MenuListDTO(
        val menuId: Long,
        val menuName: String,
        val cost: Long,
        val menuImgUrl: String?
    )
}
