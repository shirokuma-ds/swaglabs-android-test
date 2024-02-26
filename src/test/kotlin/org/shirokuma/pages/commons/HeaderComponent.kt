package org.shirokuma.pages.commons

import io.appium.java_client.AppiumBy
import org.shirokuma.bases.BasePage

class HeaderComponent : BasePage() {
    val btnCarts = create(AppiumBy.accessibilityId("test-Cart"))
    val txtCartsCount = create(AppiumBy.xpath("//*[@content-desc='test-Cart']//android.widget.TextView"))
    val btnMenu = create(AppiumBy.accessibilityId("test-Menu"))
    fun cartsCount(): Int = if (txtCartsCount.exist()) {
        txtCartsCount.text.toInt()
    } else {
        0
    }

    override fun trait() = btnMenu
}