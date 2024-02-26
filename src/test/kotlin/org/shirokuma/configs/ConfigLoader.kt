package org.shirokuma.configs

import com.google.gson.Gson
import java.io.InputStreamReader

/**
 * Load config file from the resource and map it to target class
 * Driver Config is the configs related to driver such as Appiym URL & Capabilities
 * Test Config is the configs related to the test such as Timeout & Polling
 */
object ConfigLoader {
    val driverConfig: DriverConfig = readConfig("configs/driver_config.json", DriverConfig::class.java)
    val testConfig: TestConfig = readConfig("configs/test_config.json", TestConfig::class.java)

    private fun <T> readConfig(configPath:String, clazz: Class<T>): T {
        return Gson().fromJson(
            clazz.classLoader.getResourceAsStream(configPath)
                ?.let { InputStreamReader(it) },
            clazz
        )
    }
}