package org.shirokuma.bases

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.shirokuma.drivers.DriverManager

abstract class BaseUser(capabilities: DesiredCapabilities?) {
    var driver: AndroidDriver? = null

    /**
     * When a user object is instantiated it will also trigger driver initialization
     */
    init {
        driver = DriverManager.createDriver(capabilities)
    }
}