package com.thoughtworks.car.core.utils

import com.thoughtworks.car.core.BuildConfig

fun isDevEnvironment() = BuildConfig.BUILD_TYPE == "debug"