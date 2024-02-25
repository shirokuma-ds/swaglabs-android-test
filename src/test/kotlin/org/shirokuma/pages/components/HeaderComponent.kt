package org.shirokuma.pages.components

import io.appium.java_client.AppiumBy
import org.shirokuma.pages.BasePage
import org.shirokuma.pages.ElementFacade

class HeaderComponent : BasePage() {
    val txtCartsCount = create(AppiumBy.xpath("//*[@content-desc='test-Cart']//android.widget.TextView"))
    val btnMenu = create(AppiumBy.accessibilityId("test-Menu"))
    fun cartsCount(): Int = if (txtCartsCount.exist()) {
        txtCartsCount.text.toInt()
    } else {
        0
    }
    override fun trait() = btnMenu
}