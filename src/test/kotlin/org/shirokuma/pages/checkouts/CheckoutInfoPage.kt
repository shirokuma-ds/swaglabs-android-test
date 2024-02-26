package org.shirokuma.pages.checkouts

import io.appium.java_client.AppiumBy
import org.shirokuma.bases.BasePage

class CheckoutInfoPage : BasePage() {
    val fieldFirstName = create(AppiumBy.accessibilityId("test-First Name"))
    val fieldLastName = create(AppiumBy.accessibilityId("test-Last Name"))
    val fieldPostalCode = create(AppiumBy.accessibilityId("test-Zip/Postal Code"))
    val btnCancel = create(AppiumBy.xpath("//*[@content-desc='test-CANCEL']/*"))
    val btnContinue = create(AppiumBy.xpath("//*[@content-desc='test-CONTINUE']/*"))
    override fun trait() = create(AppiumBy.xpath("//*[@text='CHECKOUT: INFORMATION']"))
}