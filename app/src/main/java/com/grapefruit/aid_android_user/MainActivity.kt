package com.grapefruit.aid_android_user

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.grapefruit.aid_android_user.databinding.ActivityMainBinding
import com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.QrScanActivity
import com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.ShopSelectActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent: Intent = Intent(this@MainActivity, QrScanActivity::class.java)
        startActivity(intent)
        finish()
    }
}