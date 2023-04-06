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
    alias(libs.plugins.router)
}

apply(from = "../config/jacoco/modules.kts")

androidApplication {
    defaultConfig {
        applicationId = "com.thoughtworks.car"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    enableCompose()

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("android.car.jar"))))

    implementation(project(":ui"))
    implementation(project(":core"))

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.router)
    kapt(libs.router.compiler)

    implementation(libs.bundles.coil)
    implementation(libs.chip.navigation)
    implementation(libs.blind.hmi.ui)

    testImplementation(project(":core-testing"))
    testImplementation(libs.junit4)

    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.truth)

    detektPlugins(libs.detekt.formatting)
}
