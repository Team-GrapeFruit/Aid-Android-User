package com.grapefruit.aid_android_user.feature_qrcode_scan.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding

class QrScanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQrscanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent: Intent = Intent(this@QrScanActivity,ShopSelectActivity::class.java)
        intent.putExtra("storeId","2")
        startActivity(intent)
    }
}