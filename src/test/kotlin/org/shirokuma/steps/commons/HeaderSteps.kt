package org.shirokuma.steps.commons

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.shirokuma.bases.BaseStep
import org.shirokuma.pages.commons.HeaderComponent
import java.time.Duration

class HeaderSteps : BaseStep() {
    private val headerComponent by lazy { HeaderComponent() }

    @Step("[Header] Header is displayed on screen is {expected}")
    fun isHeaderDisplayed(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(headerComponent.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Header] Click on Carts icon")
    fun clickOnCarts() {
        headerComponent.btnCarts.click()
    }
}