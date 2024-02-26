package org.shirokuma.drivers

import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.shirokuma.configs.ConfigLoader
import java.net.MalformedURLException
import java.net.URL

object DriverManager {

    lateinit var driver: AndroidDriver
    lateinit var appPackage: String

    fun createDriver(additionalCaps: DesiredCapabilities? = null): AndroidDriver? {
        val desiredCapabilities = DesiredCapabilities()
        ConfigLoader.driverConfig.caps.keys.forEach { key ->
            desiredCapabilities.setCapability(key, ConfigLoader.driverConfig.caps[key])
        }
        additionalCaps.let { desiredCapabilities.merge(it) }
        desiredCapabilities.setCapability("automationName", "UiAutomator2")
        appPackage = desiredCapabilities.getCapability("appPackage").toString()
        return try {
            driver = AndroidDriver(URL(ConfigLoader.driverConfig.url), desiredCapabilities)
            driver
        } catch (e: MalformedURLException) {
            println(e)
            null
        }
    }

}