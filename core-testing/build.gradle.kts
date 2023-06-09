import com.thoughtworks.car.androidLibrary

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
}

androidLibrary()

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    api(libs.bundles.unit.test)
    api(libs.core.testing)
    api(libs.compose.ui.test)
    api(libs.hilt.testing)
    detektPlugins(libs.detekt.formatting)
}