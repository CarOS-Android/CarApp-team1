#! /bin/bash

# 0. require the adb root permission
adb root

# 1. build the apk
./gradlew :app:assembleDevDebug

# 2. remount the emulator file system
adb root
adb remount

# 3. push the apk file to system/app/ directory
adb push app/build/outputs/apk/dev/debug/app-dev-debug.apk /system/app/CarApp-team1

# 4. reboot the emulator
adb reboot
