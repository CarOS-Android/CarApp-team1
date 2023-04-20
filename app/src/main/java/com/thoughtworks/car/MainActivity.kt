package com.thoughtworks.car

import android.content.Intent
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.router.Router
import com.thoughtworks.ark.router.annotation.Scheme
import com.thoughtworks.car.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@Scheme(Schemes.MAIN)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val selectedItemId by lazy {
        intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_dashboard)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        immersive()
        binding.navigationBar.setOnItemSelectedListener { itemId ->
            when (itemId) {
                R.id.navigation_vehicle -> {
                    Router.scheme(Schemes.VEHICLE)
                        .container(R.id.container)
                        .group()
                        .route(this)
                }
                R.id.navigation_navi -> {
                    Router.scheme(Schemes.NAVI)
                        .container(R.id.container)
                        .group()
                        .route(this)
                }
                R.id.navigation_dashboard -> {
                    Router.scheme(Schemes.DASHBOARD)
                        .container(R.id.container)
                        .group()
                        .route(this)
                }
                R.id.navigation_music -> {
                    Router.scheme(Schemes.MUSIC)
                        .container(R.id.container)
                        .group()
                        .route(this)
                }
                R.id.navigation_settings -> {
                    Router.scheme(Schemes.SETTINGS)
                        .container(R.id.container)
                        .group()
                        .route(this)
                }
            }
        }
        binding.navigationBar.setItemSelected(selectedItemId, true)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val newSelectedItemId = intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_dashboard)
        binding.navigationBar.setItemSelected(newSelectedItemId, true)
    }

    private fun immersive() {
        window.insetsController?.let {
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            window.navigationBarColor = getColor(android.R.color.transparent)
            it.hide(WindowInsets.Type.navigationBars())
        }
    }

    companion object {
        const val KEY_SELECTED_ITEM_ID = "selected_item_id"
    }
}
