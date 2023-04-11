# CarApp-team1

## System Requirements
* Ruby 2.6.5+
* Bundler 2.3.19+

## Setup(Mac)
- Install ruby & bundler
  ```sh
  brew install ruby
  gem install bundler
  ```
- Setup project
  go to project root dir, run this command in your terminal.
  ```sh
  ./scripts/setup.sh
  ```

## Docs
[WIKI](https://github.com/TW-Smart-CoE/ARK-WIKI)

## Deploy app to emulator system app

### initialization work (only first time)

1. run `emulator -avd Automotive_API_33 -writable-system` command to launch the emulator with writable file system.
> NOTE: put the Android SDK emulator command to the environment variable

2. run `adb root` and `adb remount` commands to remount the emulator file system
3. run `adb shell mkdir /system/app/CarApp-team1` to create the app directory

### in your develop work

run the deploy-system-app.sh shell script

### UI development

1. option1 set `hw.lcd.density`=160 that in /Users/{Username}/.android/avd/Automotive_API_33.avd/config.ini
2. option2 run `adb shell wm density 160`
3. according to the px value provided by figma, use 1:1 dp


## LICENSE

    Copyright 2022 Thoughtworks, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
