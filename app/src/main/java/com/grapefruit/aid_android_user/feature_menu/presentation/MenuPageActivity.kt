package com.grapefruit.aid_android_user.feature_menu.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivityMenuPageBinding
import com.grapefruit.aid_android_user.databinding.MenuItemListBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.adaper.menu.MenuAdapter
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel
import com.grapefruit.aid_android_user.view.chat.ChatAcitivity

class MenuPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val storeId = 1L
        val categoryId = 1L

        /*val menu = CommunicationWork()
        menu.menulist(storeId, this@MenuPageActivity)
        menu.category(categoryId, this@MenuPageActivity)*/

        val viewModel by viewModels<MenuPageViewModel>()
        viewModel.menuListRoad(storeId)
        //viewModel.categoryMenuListRoad(categoryId)

        viewModel.menuListResponse.observe(this) {
            Log.d("minseok", "observe")
            Log.d("minseok", it.toString())
            val menuAdapter = MenuAdapter(ArrayList(it))
            binding.menuList.layoutManager = LinearLayoutManager(this)
            menuAdapter.setItemOnClickListener(object : MenuAdapter.OnItemClickListener {
                override fun onClick(position: Int) {
                    val intent = Intent(this@MenuPageActivity, MenuDetailPageActivity::class.java)
                    Log.d("testt",it[position].toString())
                    //intent.putExtra("data", it[position])
                    startActivity(intent)
                }
            })
            binding.menuList.adapter = menuAdapter
        }

        with(binding) {
            backBtn.setOnClickListener {
                val intent = Intent(this@MenuPageActivity, ChatAcitivity::class.java)
                startActivity(intent)
            }

            cartImage.setOnClickListener {
                val intent = Intent(this@MenuPageActivity, ShoppingBasketPageActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

