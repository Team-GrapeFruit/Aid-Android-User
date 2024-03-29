package com.grapefruit.aid_android_user.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.databinding.ActivityMenuDetailPageBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.MenuPageViewModel


class MenuDetailPageActivity : AppCompatActivity() {

    val viewModel by viewModels<MenuPageViewModel>()
    lateinit var binding: ActivityMenuDetailPageBinding
    private var menuName = ""
    private var cost = 0L
    private var imgUrl = ""
    private var menuId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuDetailPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        menuId = intent.getLongExtra("menuId", 0)
        viewModel.menuDetailRoad(menuId!!.toLong())

        with(binding) {

            viewModel.menuDetailResponse.observe(this@MenuDetailPageActivity) {
                this@MenuDetailPageActivity.menuName = it.menuName
                this@MenuDetailPageActivity.cost = it.cost
                this@MenuDetailPageActivity.imgUrl = it.menuImgUrl ?: ""
                menuName.text = it.menuName
                menuDescription.text = it.description
                cost.text = it.cost.toString() + "원"
                Glide.with(menuImage)
                    .load(it.menuImgUrl)
                    .into(menuImage)
            }
            changeOptionBtn.setOnClickListener {
                val intent =
                    Intent(this@MenuDetailPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }
            cartBtn.setOnClickListener {
                viewModel.orderMenuToPurchaseRoad(1L, menuId, 1L)
            }
            cartImage.setOnClickListener {
                val intent =
                    Intent(this@MenuDetailPageActivity, ShoppingBasketPageActivity::class.java)
                startActivity(intent)
            }
            backBtn.setOnClickListener {
                val intent =
                    Intent(this@MenuDetailPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}