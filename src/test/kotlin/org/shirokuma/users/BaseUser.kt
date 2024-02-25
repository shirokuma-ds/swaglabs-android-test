package org.shirokuma.users

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.shirokuma.drivers.DriverManager

abstract class BaseUser(capabilities: DesiredCapabilities?) {
    var driver: AndroidDriver? = null

    init {
        driver = DriverManager.createDriver(capabilities)
    }

}