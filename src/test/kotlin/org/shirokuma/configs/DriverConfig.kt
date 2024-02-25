package org.shirokuma.configs

data class DriverConfig(
    val url: String,
    val caps: MutableMap<String, String>
)