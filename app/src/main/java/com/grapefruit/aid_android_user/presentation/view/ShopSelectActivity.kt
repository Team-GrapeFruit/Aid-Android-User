package com.grapefruit.aid_android_user.presentation.view

import android.content.Intent
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

        val storeId = intent.getStringExtra("storeId")
        Log.d("ShopAct_store",storeId.toString())

        if(storeId != null) {
            viewModel.storeLoad(storeId!!.toLong())
        }
        viewModel.storeInfo.observe(this){
            Log.d("ShopAct_it", it.toString())
        }
        binding.btnQrCheck.setOnClickListener {
            Log.d("ShopAct_click","yes")
            val intent = Intent(this@ShopSelectActivity, SeatSelectionActivity::class.java)
            intent.putExtra("storeId", storeId)
            startActivity(intent)
        }
    }
}
