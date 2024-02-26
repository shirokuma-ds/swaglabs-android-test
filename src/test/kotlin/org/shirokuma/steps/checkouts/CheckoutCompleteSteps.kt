package org.shirokuma.steps.checkouts

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.shirokuma.bases.BaseStep
import org.shirokuma.pages.checkouts.CheckoutCompletePage
import java.time.Duration

class CheckoutCompleteSteps : BaseStep() {
    private val checkoutCompletePage by lazy { CheckoutCompletePage() }

    @Step("[Checkout Complete] User is in checkout complete screen is {expected}")
    fun inCheckoutCompleteScreen(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        MatcherAssert.assertThat(checkoutCompletePage.isScreenDisplayed(expected, duration), Matchers.`is`(expected))
    }

    @Step("[Checkout Complete] Verify Checkout Complete screen")
    fun verifyCheckoutComplete() {
        softly.assertThat(checkoutCompletePage.txtTitle.text).isEqualTo("THANK YOU FOR YOUR ORDER")
        softly.assertThat(checkoutCompletePage.txtDesc.text)
            .isEqualTo("Your order has been dispatched, and will arrive just as fast as the pony can get there!")
        softly.assertThat(checkoutCompletePage.elImg.exist()).isTrue
        softly.assertThat(checkoutCompletePage.btnBackHome.exist()).isTrue
        softly.assertAll()
    }
}