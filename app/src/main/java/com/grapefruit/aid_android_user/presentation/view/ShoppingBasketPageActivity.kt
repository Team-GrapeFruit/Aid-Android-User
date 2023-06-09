package com.grapefruit.aid_android_user.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.grapefruit.aid_android_user.databinding.ActivityShoppingBasketPageBinding
import com.grapefruit.aid_android_user.presentation.view.adapter.PurchaseAdapter
import com.grapefruit.aid_android_user.presentation.viewmodel.MenuPageViewModel

class ShoppingBasketPageActivity : AppCompatActivity() {

    lateinit var bining: ActivityShoppingBasketPageBinding
    private val viewModel by viewModels<MenuPageViewModel>()

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
                val intent = Intent(this@ShoppingBasketPageActivity, ChatActivity::class.java)
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