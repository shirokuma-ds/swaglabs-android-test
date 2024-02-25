package org.shirokuma.pages

import io.appium.java_client.AppiumBy

class LoginPage : BasePage() {

    val fieldUsername = create(AppiumBy.accessibilityId("test-Username"))
    val fieldPassword = create(AppiumBy.accessibilityId("test-Password"))
    val btnLogin = create(AppiumBy.accessibilityId("test-LOGIN"))
    val txtBtnLogin = create(AppiumBy.xpath("//*[@content-desc='test-LOGIN']/*"))
    override fun trait() = btnLogin
}