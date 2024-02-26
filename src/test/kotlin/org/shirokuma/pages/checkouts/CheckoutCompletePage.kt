package org.shirokuma.pages.checkouts

import io.appium.java_client.AppiumBy
import org.shirokuma.bases.BasePage

class CheckoutCompletePage : BasePage() {
    val txtTitle = create(AppiumBy.xpath("//*[@content-desc='test-CHECKOUT: COMPLETE!']//android.widget.TextView[1]"))
    val txtDesc = create(AppiumBy.xpath("//*[@content-desc='test-CHECKOUT: COMPLETE!']//android.widget.TextView[2]"))
    val elImg = create(AppiumBy.xpath("//*[@content-desc='test-CHECKOUT: COMPLETE!']//android.widget.ImageView[1]"))
    val btnBackHome = create(AppiumBy.xpath("//*[@content-desc='test-BACK HOME']/*"))
    override fun trait() = create(AppiumBy.xpath("//*[@text='CHECKOUT: COMPLETE!']"))
}