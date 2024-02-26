package org.shirokuma.pages.checkouts

import io.appium.java_client.AppiumBy
import org.shirokuma.bases.BasePage

class CartsPage : BasePage() {
    val elCartItems = create(AppiumBy.accessibilityId("test-Item"))
    val txtItemName = create(AppiumBy.xpath("//*[@content-desc='test-Description']/*[1]"))
    val txtItemPrice = create(AppiumBy.xpath("//*[@content-desc='test-Price']/*[1]"))
    val txtItemQty = create(AppiumBy.xpath("//*[@content-desc='test-Amount']/*[1]"))
    val btnRemove = create(AppiumBy.accessibilityId("test-REMOVE"))

    val btnCheckout = create(AppiumBy.xpath("//*[@content-desc='test-CHECKOUT']/*"))
    override fun trait() = create(AppiumBy.xpath("//*[@text='YOUR CART']"))
}