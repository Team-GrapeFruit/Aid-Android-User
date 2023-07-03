package com.grapefruit.aid_android_user.presentation.view

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.grapefruit.aid_android_user.databinding.ActivityQrscanBinding
import com.grapefruit.aid_android_user.presentation.viewmodel.QrcodeViewModel


class QrScanActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: ActivityQrscanBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrscanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        setupPermissions()
        setupCodeScanner()

    }

    private fun setupPermissions() {
        val cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1004)
        }
    }

    private fun setupCodeScanner() {
        val codeScannerView = binding.codeScannerView
        codeScanner = CodeScanner(this, codeScannerView)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS
            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                runOnUiThread {
                    val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreference.edit()
                    val barcodeValue = it.text
                    Log.d("testt",it.toString())
                    editor.putLong("storeId",barcodeValue.toLong())
                    editor.commit()
                    val intent = Intent(this@QrScanActivity,ShopSelectActivity::class.java)
                    startActivity(intent)
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("QR", it.message!!)
                }
            }

            codeScannerView.setOnClickListener {
                codeScanner.startPreview()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized) {
            codeScanner.startPreview()
        }
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1004 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupCodeScanner()
                } else {
                    finish()
                }
            }
            else -> {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }
}