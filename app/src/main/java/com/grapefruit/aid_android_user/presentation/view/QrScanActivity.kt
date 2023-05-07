package com.grapefruit.aid_android_user.presentation.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector

import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding
import com.journeyapps.barcodescanner.*
import java.io.IOException


class QrScanActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private lateinit var cameraSource: CameraSource
    private lateinit var surfaceView: SurfaceView
    private var isScanned: Boolean = false
    private lateinit var binding: ActivityQrscanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        surfaceView = binding.surfaceView
        surfaceView.holder.addCallback(this)

        checkPermission()
    }

    private fun checkPermission() {
        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // 카메라 권한이 승인된 상태일 경우
            setupControls()
        } else {
            // 카메라 권한이 승인되지 않았을 경우
            requestPermission()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1004)
    }

    private fun setupControls() {
        val barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.QR_CODE)
            .build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setAutoFocusEnabled(true)
            .build()

        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                cameraSource.stop()
                isScanned = false
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                if (true) {
                    isScanned = true
                    val barcodes = detections.detectedItems
                    if (barcodes.size() != 0) {
                        print("test")
                        val barcodeValue = barcodes.valueAt(0).displayValue // 스캔된 바코드 값
                        val intent = Intent(this@QrScanActivity, ShopSelectActivity::class.java)
                        intent.putExtra("storeId", barcodeValue)
                        startActivity(intent)
                        release()
                        finish()
                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            1004 ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupControls()
                } else {
                    finish()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        //checkPermission()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

}