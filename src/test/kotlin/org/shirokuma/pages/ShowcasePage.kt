package org.shirokuma.pages

import io.appium.java_client.AppiumBy
import org.shirokuma.pages.components.HeaderComponent

class ShowcasePage : BasePage() {
    val header by lazy { HeaderComponent() }
    val elProducts = create(AppiumBy.xpath("//*[@content-desc='test-Item']"))
    val txtProductName = create(AppiumBy.xpath("//*[@content-desc='test-Item title']"))
    val txtProductPrice = create(AppiumBy.xpath("//*[@content-desc='test-Price']"))
    val btnAddToCart = create(AppiumBy.accessibilityId("test-ADD TO CART"))
    val btnRemove = create(AppiumBy.accessibilityId("test-REMOVE"))
    override fun trait() = create(AppiumBy.accessibilityId("test-Cart drop zone"))
}