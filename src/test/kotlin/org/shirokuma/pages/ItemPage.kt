package org.shirokuma.pages

import io.appium.java_client.AppiumBy
import org.shirokuma.pages.components.HeaderComponent

class ItemPage : BasePage() {
    val header by lazy { HeaderComponent() }
    val txtItemName = create(AppiumBy.xpath("//*[@content-desc='test-Description']/android.widget.TextView[1]"))
    val txtItemPrice = create(AppiumBy.accessibilityId("test-Price"))
    val btnAddToCard = create(AppiumBy.accessibilityId("test-ADD TO CART"))
    val btnRemove = create(AppiumBy.accessibilityId("test-REMOVE"))
    val btnBackToProducts = create(AppiumBy.accessibilityId("test-BACK TO PRODUCTS"))
    override fun trait() = create(AppiumBy.xpath("//*[@content-desc='test-Inventory item page']"))
}