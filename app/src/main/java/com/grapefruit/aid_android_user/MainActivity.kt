package com.grapefruit.aid_android_user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grapefruit.aid_android_user.feature_qrcode_scan.presentation.QrScanActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent: Intent = Intent(this@MainActivity,QrScanActivity::class.java)
        startActivity(intent)
    }
}