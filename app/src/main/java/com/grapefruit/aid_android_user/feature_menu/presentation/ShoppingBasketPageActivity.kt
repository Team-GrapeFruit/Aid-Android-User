package com.grapefruit.aid_android_user.feature_menu.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.grapefruit.aid_android_user.databinding.ActivityShoppingBasketPageBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseSeatDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.adaper.purchase.PurchaseAdapter
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel
import com.grapefruit.aid_android_user.view.chat.ChatAcitivity

class ShoppingBasketPageActivity : AppCompatActivity() {

    lateinit var bining: ActivityShoppingBasketPageBinding
    lateinit var viewModel: MenuPageViewModel
    lateinit var list: List<PurchaseSeatDTO>

    override fun onCreate(savedInstanceState: Bundle?) {
        bining = ActivityShoppingBasketPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(bining.root)

        //list.

        val viewModel by viewModels<MenuPageViewModel>()
        viewModel.purchaseListRoad(1L)
        //viewModel.purchaseSeatRoad(1L, )

        viewModel.purchaseListResponse.observe(this) {
            Log.d("minseok", "observe")
            Log.d("minseok", it.toString())
            val purchaseAdapter = PurchaseAdapter(ArrayList(it))
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
                val intent = Intent(this@ShoppingBasketPageActivity, ChatAcitivity::class.java)
                startActivity(intent)
            }
        }
    }
}