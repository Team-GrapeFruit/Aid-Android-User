package com.grapefruit.aid_android_user.feature_menu.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.grapefruit.aid_android_user.databinding.ActivityShoppingBasketPageBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseSeatDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.adaper.purchase.PurchaseAdapter
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel
import com.grapefruit.aid_android_user.view.chat.ChatAcitivity

class ShoppingBasketPageActivity : AppCompatActivity() {

    lateinit var bining: ActivityShoppingBasketPageBinding
    private val viewModel by viewModels<MenuPageViewModel>()
    lateinit var list: List<PurchaseSeatDTO>

    override fun onCreate(savedInstanceState: Bundle?) {
        bining = ActivityShoppingBasketPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bining.root)

        viewModel.purchaseListRoad(1L)

        viewModel.purchaseListResponse.observe(this) {
            val purchaseAdapter = PurchaseAdapter(ArrayList(it), this)
            bining.menuList.layoutManager = LinearLayoutManager(this)
            bining.menuList.adapter = purchaseAdapter
        }

        with(bining) {
            backBtn.setOnClickListener {
                val intent = Intent(this@ShoppingBasketPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }

            moreMenu.setOnClickListener {
                val intent = Intent(this@ShoppingBasketPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }

            orderBtn.setOnClickListener {
                // viewModel.order(1L)
                val intent = Intent(this@ShoppingBasketPageActivity, ChatAcitivity::class.java)
                startActivity(intent)
            }
        }
    }

    fun deleteItem(position: Int) {
        viewModel.deleteMenuRoad(position.plus(1).toLong())
        viewModel.purchaseListRoad(1L)
    }

    fun quantityControl(position: Int, quantity: Long) {
        viewModel.quantityControlRoad(position.plus(1).toLong(), quantity)
        viewModel.purchaseListRoad(1L)
    }
}