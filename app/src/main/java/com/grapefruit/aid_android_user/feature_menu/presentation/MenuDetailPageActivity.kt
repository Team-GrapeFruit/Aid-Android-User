package com.grapefruit.aid_android_user.feature_menu.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.databinding.ActivityMenuDetailPageBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.PurchaseMenuDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel


class MenuDetailPageActivity : AppCompatActivity() {

    val viewModel by viewModels<MenuPageViewModel>()
    lateinit var binding: ActivityMenuDetailPageBinding
    private var menuName = ""
    private var cost = 0L
    private var imgUrl = ""
    var menuId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuDetailPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        menuId = intent.getLongExtra("menuId",0)
        viewModel.menuDetailRoad(menuId)

        with(binding) {

            viewModel.menuDetailResponse.observe(this@MenuDetailPageActivity) {
                Log.d("detail-info", it.toString())
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
                /*viewModel.addMenuToBasket(menuInfo = PurchaseMenuDTO(1L, 1L))
                viewModel.addMenuToMenuInfoList(
                    purchaseId = 1L,
                    quantity = 1L,
                    menuInfo = MenuDTO(
                        menuId = 0L,
                        menuName = this@MenuDetailPageActivity.menuName,
                        cost = this@MenuDetailPageActivity.cost,
                        menuImgURL = this@MenuDetailPageActivity.imgUrl
                    )
                )
                Log.d("cartBtn", viewModel.menuInfoList.toString())*/
                viewModel.orderMenuToPurchaseRoad(1L, 1L, 1L)
            }
            cartImage.setOnClickListener {
                // Log.d("cartImage", viewModel.menuInfoList.toString())
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