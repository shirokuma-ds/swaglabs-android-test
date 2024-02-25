package org.shirokuma.steps

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.OutputType
import org.shirokuma.pages.ShowcasePage
import org.shirokuma.testData.products.ItemDetail
import java.time.Duration

class ShowcaseSteps : BaseStep() {

    private val showcasePage by lazy { ShowcasePage() }

    @Step("[Products] User is in products screen is {expected}")
    fun inShowcaseScreen(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(showcasePage.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Products] Click on item {number}")
    fun clickOnItem(number: Int) {
        showcasePage.elProducts.toElements()[number - 1].click()
    }

    @Step("[Products] Check item {number} detail")
    fun checkItemDetail(number: Int, addedToCart: Boolean = false): ItemDetail {
        val elItem = showcasePage.elProducts.toElements()[number - 1]
        showcasePage.isDisplayedOnScreen(elItem)
        val itemImage = elItem.findElement(By.xpath("//*[@class='android.widget.ImageView']"))
        val itemImageScreenshot = itemImage.getScreenshotAs(OutputType.FILE)
        val itemName = elItem.findElement(showcasePage.txtProductName.locator).text
        val itemPrice = elItem.findElement(showcasePage.txtProductPrice.locator).text
        val isBtnAddToCartExist = try {
            val btnAddToCart = elItem.findElement(showcasePage.btnAddToCart.locator)
            btnAddToCart.isDisplayed
        } catch (e: NoSuchElementException) {
            false
        }
        val isBtnRemoveExist = try {
            val btnRemove = elItem.findElement(showcasePage.btnRemove.locator)
            btnRemove.isDisplayed
        } catch (e: NoSuchElementException) {
            false
        }
        softly.assertThat(isBtnAddToCartExist).isNotEqualTo(addedToCart)
        softly.assertThat(isBtnRemoveExist).isEqualTo(addedToCart)
        softly.assertAll()
        return ItemDetail(itemName, itemPrice)
    }

}