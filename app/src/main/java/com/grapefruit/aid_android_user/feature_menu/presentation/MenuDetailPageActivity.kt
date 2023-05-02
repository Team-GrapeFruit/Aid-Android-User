package com.grapefruit.aid_android_user.feature_menu.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.databinding.ActivityMenuDetailPageBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel

class MenuDetailPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuDetailPageBinding
    lateinit var viewModel: MenuPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuDetailPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            val viewModel by viewModels<MenuPageViewModel>()
            viewModel.menuDetailRoad(1L)

            viewModel.menuListResponse.observe(this@MenuDetailPageActivity) {
                Log.d("detail-info", it.toString())
//                menuName.text = it[0].menuName
//                menuDescription.text = it[0].description
//                cost.text = it[0].cost.toString()
//                Glide.with(menuImage)
//                    .load(it[0].menuImgURL)
//                    .into(menuImage)
            }

            changeOptionBtn.setOnClickListener {
                val intent = Intent(this@MenuDetailPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }
            cartBtn.setOnClickListener {

            }
            cartImage.setOnClickListener {
                val intent =
                    Intent(this@MenuDetailPageActivity, ShoppingBasketPageActivity::class.java)
                startActivity(intent)
            }
            backBtn.setOnClickListener {
                val intent = Intent(this@MenuDetailPageActivity, MenuPageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}