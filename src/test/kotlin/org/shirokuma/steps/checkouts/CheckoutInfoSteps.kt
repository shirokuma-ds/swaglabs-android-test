package org.shirokuma.steps.checkouts

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.shirokuma.bases.BaseStep
import org.shirokuma.pages.checkouts.CheckoutInfoPage
import java.time.Duration

class CheckoutInfoSteps : BaseStep() {

    private val checkoutInfoPage by lazy { CheckoutInfoPage() }

    @Step("[Checkout Info] User is in checkout info screen is {expected}")
    fun inCheckoutInfoScreen(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(checkoutInfoPage.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Checkout Info] Fill the checkout information")
    fun fillCheckoutInfo(firstName: String, lastName: String, postalCode: String) {
        checkoutInfoPage.fieldFirstName.sendKeys(firstName)
        checkoutInfoPage.fieldLastName.sendKeys(lastName)
        checkoutInfoPage.fieldPostalCode.sendKeys(postalCode)
        checkoutInfoPage.btnContinue.click()
    }
}