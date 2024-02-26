package org.shirokuma.pages.commons

import io.appium.java_client.AppiumBy
import org.shirokuma.bases.BasePage

class MenuPage : BasePage() {
    val btnLogout = create(AppiumBy.xpath("//*[@content-desc='test-LOGOUT']/*[1]"))
    override fun trait() = btnLogout
}