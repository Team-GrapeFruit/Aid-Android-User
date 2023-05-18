package com.grapefruit.aid_android_user.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.grapefruit.aid_android_user.databinding.ActivityShopSelectBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.QrcodeViewModel

class ShopSelectActivity : AppCompatActivity() {
    private val viewModel by viewModels<QrcodeViewModel>()

    private lateinit var binding: ActivityShopSelectBinding
    private var isSuccess = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val storeId = intent.getLongExtra("storeId",0)
        Log.d("ShopAct_store",storeId.toString())

        if(storeId != null) {
            viewModel.storeLoad(storeId)
        }
        viewModel.isSuccess.observe(this){
            Log.d("ShopAct_it", it.toString())
            isSuccess = it
        }
        binding.btnQrCheck.setOnClickListener {
            if(isSuccess){
                val intent: Intent = Intent(this@ShopSelectActivity,SeatSelectionActivity::class.java)
                intent.putExtra("storeId",storeId)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "가게를 찾을수 없습니다.", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
