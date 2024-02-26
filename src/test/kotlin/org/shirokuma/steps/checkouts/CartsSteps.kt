package org.shirokuma.steps.checkouts

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.shirokuma.bases.BaseStep
import org.shirokuma.bases.ElementFacade
import org.shirokuma.pages.checkouts.CartsPage
import org.shirokuma.testData.products.ItemDetail
import org.shirokuma.utils.Swipe
import org.shirokuma.utils.SwipeDirection
import java.time.Duration

class CartsSteps : BaseStep() {

    private val cartsPage by lazy { CartsPage() }

    @Step("[Carts] User is in carts screen is {expected}")
    fun inCartsScreen(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(cartsPage.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Carts] Check that cart item detail and quantity is correct")
    fun checkCartItemDetail(itemDetail: ItemDetail, maxAttempt: Int = 10) {
        var attempt = 0
        while (attempt++ < maxAttempt && !cartsPage.elCartItems.toElements().any {
                it.findElement(cartsPage.txtItemName.locator).text == itemDetail.name
            }) {
            Swipe.swipe(SwipeDirection.UP)
        }

        val elItem = cartsPage.elCartItems.toElements().firstOrNull {
            it.findElement(cartsPage.txtItemName.locator).text == itemDetail.name
        }

        assertThat(elItem, `is`(notNullValue()))
        elItem?.let {
            Swipe.swipeIntoView(ElementFacade.fromWebElement(it), SwipeDirection.UP)
            softly.assertThat(it.findElement(cartsPage.txtItemName.locator).text).isEqualTo(itemDetail.name)
            softly.assertThat(it.findElement(cartsPage.txtItemPrice.locator).text).isEqualTo(itemDetail.price)
            softly.assertThat(it.findElement(cartsPage.txtItemQty.locator).text.toInt()).isEqualTo(itemDetail.qty)
            softly.assertAll()
        }
    }

    @Step("[Carts] Click on button checkout")
    fun clickOnBtnCheckout() {
        Swipe.swipeIntoView(cartsPage.btnCheckout, SwipeDirection.UP)
        assertThat(cartsPage.btnCheckout.text, equalTo("CHECKOUT"))
        cartsPage.btnCheckout.click()
    }

}