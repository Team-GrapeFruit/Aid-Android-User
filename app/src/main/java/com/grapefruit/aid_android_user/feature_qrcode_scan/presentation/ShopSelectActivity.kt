package com.grapefruit.aid_android_user.feature_qrcode_scan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding
import com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.vm.QrcodeViewModel

class ShopSelectActivity : AppCompatActivity() {
    private val viewModel by viewModels<QrcodeViewModel>()

    private lateinit var binding: ActivityQrscanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.getStringExtra("storeId").toString()
        Log.d("testt",storeId)
        viewModel.storeLoad(storeId)

        viewModel.storeInfo.observe(this){
            Log.d("tests",it.toString())
        }
    }
}