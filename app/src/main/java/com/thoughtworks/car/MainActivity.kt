package com.thoughtworks.car

import android.car.VehiclePropertyIds.HVAC_TEMPERATURE_SET
import android.car.hardware.property.CarPropertyManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.thoughtworks.ark.router.Router
import com.thoughtworks.ark.router.annotation.Scheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Scheme(Schemes.MAIN)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // should move this to ViewModel @XinWang
    @Inject
    lateinit var carPropertyManager: CarPropertyManager

    private val navView by lazy { findViewById<BottomNavigationView>(R.id.nav_view) }

    private val selectedItemId by lazy {
        intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_home)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // should remove this test code @XinWang
        val temperatureConfig = carPropertyManager.getCarPropertyConfig(HVAC_TEMPERATURE_SET)
        println("HVAC temperature config: ${temperatureConfig.configArray}")

        navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    Router.scheme(Schemes.HOME)
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
                R.id.navigation_notifications -> {
                    Router.scheme(Schemes.NOTIFICATIONS)
                        .container(R.id.container)
                        .group()
                        .route(this)
                }
            }
            true
        }
        navView.selectedItemId = selectedItemId
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val newSelectedItemId = intent.getIntExtra(KEY_SELECTED_ITEM_ID, R.id.navigation_home)
        navView.selectedItemId = newSelectedItemId
    }

    companion object {
        const val KEY_SELECTED_ITEM_ID = "selected_item_id"
    }
}