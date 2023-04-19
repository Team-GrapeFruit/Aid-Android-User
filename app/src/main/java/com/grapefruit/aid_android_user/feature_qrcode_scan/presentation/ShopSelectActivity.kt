package com.grapefruit.aid_android_user.feature_qrcode_scan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding
import com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.vm.QrcodeViewModel

class ShopSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQrscanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}