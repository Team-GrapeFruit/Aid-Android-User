package com.grapefruit.aid_android_user.data.dto


data class PurchaseDTO(
    val purchaseId: Long,
    val quantity: Long,
    val purchaseMenu: MenuDTO
)
