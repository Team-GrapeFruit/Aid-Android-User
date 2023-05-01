package com.grapefruit.aid_android_user.feature_qrcode_scan.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.grapefruit.aid_android_user.R

import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding
import com.journeyapps.barcodescanner.*
import java.io.IOException


class QrScanActivity : AppCompatActivity() {

    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var binding: ActivityQrscanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPermission()
    }

    private fun setupControls() {
        barcodeView = binding.barcodeView
        val formats = listOf(BarcodeFormat.QR_CODE)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                result?.let {
                    val barcodeValue = it.text // 스캔된 바코드 값
                    Log.d("testt", barcodeValue)
                    val intent = Intent(this@QrScanActivity, ShopSelectActivity::class.java)
                    intent.putExtra("storeId", barcodeValue)
                    startActivity(intent)
                    finish()
                }
            }

            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {

            }
        })
        barcodeView.resume()
    }

    private fun checkPermission() {
        val cameraPermission = ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // 카메라 권한이 승인된 상태일 경우
            Log.d("testt","Y")
            setupControls()
        } else {
            // 카메라 권한이 승인되지 않았을 경우
            Log.d("testt","N")
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CAMERA), 1004)
        Log.d("testt", arrayOf(Manifest.permission.CAMERA).toString())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            1004 ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupControls()
                } else {
                    Log.d("testt","end")
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

}

