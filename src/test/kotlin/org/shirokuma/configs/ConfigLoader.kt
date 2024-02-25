package org.shirokuma.configs

import com.google.gson.Gson
import java.io.InputStreamReader


object ConfigLoader {
    val driverConfig: DriverConfig = Gson().fromJson(
        DriverConfig::class.java.classLoader.getResourceAsStream("configs/driver_config.json")?.let { InputStreamReader(it) },
        DriverConfig::class.java
    )
}