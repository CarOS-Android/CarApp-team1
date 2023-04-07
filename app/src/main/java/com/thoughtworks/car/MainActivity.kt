package com.thoughtworks.car

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.thoughtworks.ark.router.Router
import com.thoughtworks.ark.router.annotation.Scheme
import dagger.hilt.android.AndroidEntryPoint

@Scheme(Schemes.MAIN)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navView by lazy { findViewById<ChipNavigationBar>(R.id.navigationBar) }

    private val selectedItemId by lazy {
        intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_dashboard)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.setOnItemSelectedListener { itemId ->
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
        navView.setItemSelected(selectedItemId, true)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val newSelectedItemId = intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_dashboard)
        navView.setItemSelected(newSelectedItemId, true)
    }

    companion object {
        const val KEY_SELECTED_ITEM_ID = "selected_item_id"
    }
}
