pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.toString() == "com.thoughtworks.ark.router") {
                useModule("com.github.TW-Smart-CoE.ARK-Android-Router:com.thoughtworks.ark.router:${requested.version}")
            }
        }
    }

    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        maven {
            setUrl("http://10.205.215.4:8081/repository/maven-releases/")
            isAllowInsecureProtocol = true
        }
    }
}

rootProject.name = "CarApp-team1"
// Main App Module
include(":app")
// UI Module for cross feature UI components
include(":ui")
// Common Module/Utils/Extensions for cross feature
include(":core")
include(":core-testing")