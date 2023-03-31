package com.thoughtworks.car.deeplink

import com.thoughtworks.ark.router.Router
import com.thoughtworks.ark.router.RouterInterceptor
import com.thoughtworks.ark.router.SchemeRequest
import com.thoughtworks.car.MainActivity
import com.thoughtworks.car.R
import com.thoughtworks.car.Schemes

class DeeplinkSchemeInterceptor : RouterInterceptor {
    override suspend fun shouldIntercept(request: SchemeRequest): Boolean {
        return request.scheme == Schemes.VEHICLE ||
            request.scheme == Schemes.NAVI ||
            request.scheme == Schemes.DASHBOARD ||
            request.scheme == Schemes.MUSIC ||
            request.scheme == Schemes.SETTINGS
    }

    override suspend fun intercept(request: SchemeRequest): SchemeRequest {
        val selectedItemId = when (request.scheme) {
            Schemes.VEHICLE -> R.id.navigation_vehicle
            Schemes.NAVI -> R.id.navigation_navi
            Schemes.DASHBOARD -> R.id.navigation_dashboard
            Schemes.MUSIC -> R.id.navigation_music
            Schemes.SETTINGS -> R.id.navigation_settings
            else -> R.id.navigation_dashboard
        }

        return Router.scheme(Schemes.MAIN)
            .params(MainActivity.KEY_SELECTED_ITEM_ID to selectedItemId)
            .request
    }
}