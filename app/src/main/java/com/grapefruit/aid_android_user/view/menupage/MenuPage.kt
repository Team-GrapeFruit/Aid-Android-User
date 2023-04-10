package com.grapefruit.aid_android_user.view.menupage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grapefruit.aid_android_user.R

class MenuPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_page)
        setContentView(R.layout.activity_menu_list_page)
    }
}