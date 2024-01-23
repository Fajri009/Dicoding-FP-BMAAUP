package com.fajri.project_dicoding_2.About

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.fajri.project_dicoding_2.R

class AboutScreen : AppCompatActivity() {
    private lateinit var ivBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_screen)

        ivBack = findViewById(R.id.iv_back)

        backButton()
    }

    private fun backButton() {
        ivBack.setOnClickListener {
            finish()
        }
    }
}