import com.thoughtworks.car.androidApplication
import com.thoughtworks.car.enableCompose

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
}

apply(from = "../config/jacoco/modules.kts")

androidApplication {
    defaultConfig {
        applicationId = "com.thoughtworks.car.sample"
        versionCode = 1
        versionName = "1.0.0"
    }

    enableCompose()
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":core"))
    implementation(project(":core-storage"))
    implementation(project(":core-permission"))
    implementation(project(":core-media"))
    implementation(project(":webview"))

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.bundles.coil)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(project(":core-testing"))
    detektPlugins(libs.detekt.formatting)
}