package com.thoughtworks.car.sample.designsystem.colorsystem.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.car.ui.theme.Theme

class ComposeColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContent {
            Theme {
                ComposeColorSystemScreen()
            }
        }
    }
}