package org.shirokuma.steps.checkouts

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.shirokuma.bases.BaseStep
import org.shirokuma.bases.ElementFacade
import org.shirokuma.pages.checkouts.CheckoutOverviewPage
import org.shirokuma.testData.products.ItemDetail
import org.shirokuma.utils.Swipe
import org.shirokuma.utils.SwipeDirection
import java.time.Duration

class CheckoutOverviewSteps : BaseStep() {

    private val checkoutOverviewPage by lazy { CheckoutOverviewPage() }

    @Step("[Checkout Overview] User is in checkout overview screen is {expected}")
    fun inCheckoutOverviewScreen(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(checkoutOverviewPage.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Checkout Overview] Check that cart item detail and quantity is correct")
    fun checkCheckoutItemDetail(itemDetail: ItemDetail, maxAttempt: Int = 10) {
        var attempt = 0
        while (attempt++ < maxAttempt && !checkoutOverviewPage.elItems.toElements().any {
                it.findElement(checkoutOverviewPage.txtItemName.locator).text == itemDetail.name
            }) {
            Swipe.swipe(SwipeDirection.UP)
        }

        val elItem = checkoutOverviewPage.elItems.toElements().firstOrNull {
            it.findElement(checkoutOverviewPage.txtItemName.locator).text == itemDetail.name
        }

        assertThat(elItem, `is`(notNullValue()))
        elItem?.let {
            Swipe.swipeIntoView(ElementFacade.fromWebElement(it), SwipeDirection.UP)
            softly.assertThat(it.findElement(checkoutOverviewPage.txtItemName.locator).text).isEqualTo(itemDetail.name)
            softly.assertThat(it.findElement(checkoutOverviewPage.txtItemPrice.locator).text)
                .isEqualTo(itemDetail.price)
            softly.assertThat(it.findElement(checkoutOverviewPage.txtItemQty.locator).text.toInt()).isEqualTo(itemDetail.qty)
            softly.assertAll()
        }
    }

    @Step("[Checkout Overview] Click on finish button")
    fun clickOnBtnFinish() {
        Swipe.swipeIntoView(checkoutOverviewPage.btnFinish, SwipeDirection.UP)
        assertThat(checkoutOverviewPage.btnFinish.text, equalTo("FINISH"))
        checkoutOverviewPage.btnFinish.click()
    }

}