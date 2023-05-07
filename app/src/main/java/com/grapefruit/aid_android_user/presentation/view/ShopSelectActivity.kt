package com.grapefruit.aid_android_user.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.grapefruit.aid_android_user.databinding.ActivityShopSelectBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.QrcodeViewModel

class ShopSelectActivity : AppCompatActivity() {
    private val viewModel by viewModels<QrcodeViewModel>()

    private lateinit var binding: ActivityShopSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.getStringExtra("storeId").toString()
        Log.d("testt_a",storeId)

        viewModel.storeLoad(storeId)
        viewModel.storeInfo.observe(this){
            Log.d("testt_sa", it.toString())
        }
    }
}