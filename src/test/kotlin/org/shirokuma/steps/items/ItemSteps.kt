package org.shirokuma.steps.items

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.shirokuma.bases.BaseStep
import org.shirokuma.pages.items.ItemPage
import org.shirokuma.testData.products.ItemDetail
import org.shirokuma.utils.Swipe
import org.shirokuma.utils.SwipeDirection
import java.time.Duration

class ItemSteps : BaseStep() {

    private val itemPage by lazy { ItemPage() }

    @Step("[Item] User is in item screen is {expected}")
    fun inItemPage(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(itemPage.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Item] Verify item detail is correct")
    fun checkItemDetail(itemDetail: ItemDetail) {
        if (!itemPage.txtItemName.isDisplayedOnScreen()) {
            Swipe.swipeIntoView(itemPage.txtItemName, SwipeDirection.UP)
        }
        softly.assertThat(itemPage.txtItemName.text).isEqualTo(itemDetail.name)
        if (!itemPage.txtItemPrice.isDisplayedOnScreen()) {
            Swipe.swipeIntoView(itemPage.txtItemPrice, SwipeDirection.UP)
        }
        softly.assertThat(itemPage.txtItemPrice.text).isEqualTo(itemDetail.price)
        softly.assertAll()
    }

    @Step("[Item] Click on button add to cart")
    fun clickOnBtnAddToCart() {
        val currTotalCartItems = itemPage.header.cartsCount()
        Swipe.swipeIntoView(itemPage.btnAddToCard, SwipeDirection.UP)
        itemPage.btnAddToCard.click()
        softly.assertThat(itemPage.btnAddToCard.exist()).isFalse
        softly.assertThat(itemPage.btnRemove.exist()).isTrue
        softly.assertThat(itemPage.header.cartsCount()).isEqualTo(currTotalCartItems + 1)
        softly.assertAll()
    }

    @Step("[Item] Click on button back to products")
    fun clickOnBtnBackToProducts() {
        itemPage.btnBackToProducts.click()
    }

}