package com.thoughtworks.car.deeplink.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.router.Router
import com.thoughtworks.car.Schemes
import com.thoughtworks.car.deeplink.DeeplinkManager
import com.thoughtworks.car.deeplink.DeeplinkSchemeInterceptor

class DeeplinkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseScheme(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        parseScheme(intent)
    }

    private fun parseScheme(intent: Intent?) {
        val originScheme = intent?.dataString ?: ""
        val destScheme = DeeplinkManager.parseDestScheme(originScheme)
        if (destScheme.isNotEmpty()) {
            Router.scheme(destScheme)
                .addInterceptor(DeeplinkSchemeInterceptor())
                .route(
                    this,
                    onError = {
                        Router.scheme(Schemes.MAIN).route(this)
                    }
                )
        } else {
            Router.scheme(Schemes.MAIN).route(this)
        }
        finish()
    }
}