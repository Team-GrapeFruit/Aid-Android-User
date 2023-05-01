package com.grapefruit.aid_android_user.feature_qrcode_scan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding
import com.grapefruit.aid_android_user.databinding.ActivityShopSelectBinding
import com.grapefruit.aid_android_user.feature_qrcode_scan.data.DTO.ShopDetail
import com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.vm.QrcodeViewModel

class ShopSelectActivity : AppCompatActivity() {
    private val viewModel by viewModels<QrcodeViewModel>()

    private lateinit var binding: ActivityShopSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.getStringExtra("storeId").toString()
        Log.d("testt_a",storeId)
        //Toast.makeText(this,storeId,Toast.LENGTH_SHORT).show()

        viewModel.storeLoad(storeId)

        viewModel.storeInfo.observe(this){
            Log.d("testt_a",it.toString())
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}