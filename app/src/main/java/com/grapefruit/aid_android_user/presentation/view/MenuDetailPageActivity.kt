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

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuDetailPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var menuId = intent.getStringExtra("menuId")
        viewModel.menuDetailRoad(menuId!!.toLong())

        with(binding) {

            viewModel.menuDetailResponse.observe(this@MenuDetailPageActivity) {
                this@MenuDetailPageActivity.menuName = it.menuName
                this@MenuDetailPageActivity.cost = it.cost
                this@MenuDetailPageActivity.imgUrl = it.menuImgURL ?: ""
                menuName.text = it.menuName
                menuDescription.text = it.description
                cost.text = it.cost.toString() + "원"
                Glide.with(menuImage)
                    .load(it.menuImgURL)
                    .into(menuImage)
            }
            changeOptionBtn.setOnClickListener {
                val intent =
                    Intent(this@MenuDetailPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }
            cartBtn.setOnClickListener {
                viewModel.orderMenuToPurchaseRoad(1L, 1L, 1L)
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