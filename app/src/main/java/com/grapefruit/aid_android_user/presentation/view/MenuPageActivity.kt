package com.grapefruit.aid_android_user.presentation.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.grapefruit.aid_android_user.databinding.ActivityMenuPageBinding
import com.grapefruit.aid_android_user.presentation.view.adapter.CategoryMenuAdapter
import com.grapefruit.aid_android_user.presentation.view.adapter.MenuAdapter
import com.grapefruit.aid_android_user.presentation.viewmodel.MenuPageViewModel

class MenuPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuPageBinding
    val viewModel by viewModels<MenuPageViewModel>()
    var storeId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        storeId = intent.getLongExtra("storeId",0)
        Log.d("storeId", storeId.toString())
        val context = this@MenuPageActivity

        viewModel.menuListRoad(storeId!!.toLong())

        viewModel.menuListResponse.observe(this) {
            Log.d("minseok", "observe")
            Log.d("minseok", it.toString())
            val menuAdapter = MenuAdapter(it)
            binding.menuList.layoutManager = LinearLayoutManager(this)
            binding.menuList.adapter = menuAdapter
        }

        with(binding) {
            backBtn.setOnClickListener {
                val intent = Intent(this@MenuPageActivity, ChatActivity::class.java)
                startActivity(intent)
            }
            cartImage.setOnClickListener {
                val intent = Intent(this@MenuPageActivity, ShoppingBasketPageActivity::class.java)
                startActivity(intent)
            }
            menuCategory1.setOnClickListener {
                viewModel.menuListResponse.observe(context) {
                    val menuAdapter = MenuAdapter(it)
                    menuList.layoutManager = LinearLayoutManager(this@MenuPageActivity)
                    menuList.adapter = menuAdapter
                }
            }
            menuCategory2.setOnClickListener {
                viewModel.categoryMenuListRoad(1L)
                viewModel.menuByCategoryResponse.observe(context) {
                    val categoryOneAdapter = CategoryMenuAdapter(it)
                    menuList.layoutManager = LinearLayoutManager(this@MenuPageActivity)
                    menuList.adapter = categoryOneAdapter
                }
            }
            menuCategory3.setOnClickListener {
                viewModel.categoryMenuListRoad(2L)
                viewModel.menuByCategoryResponse.observe(context) {
                    val categoryTwoAdapter = CategoryMenuAdapter(it)
                    menuList.layoutManager = LinearLayoutManager(this@MenuPageActivity)
                    menuList.adapter = categoryTwoAdapter
                }
            }
            menuCategory4.setOnClickListener {
                viewModel.categoryMenuListRoad(3L)
                viewModel.menuByCategoryResponse.observe(context) {
                    val categoryThreeAdapter = CategoryMenuAdapter(it)
                    menuList.layoutManager = LinearLayoutManager(this@MenuPageActivity)
                    menuList.adapter = categoryThreeAdapter
                }
            }
        }
    }
}

