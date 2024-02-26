package org.shirokuma.tests

import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.hamcrest.MatcherAssert.assertThat
import org.shirokuma.drivers.DriverManager
import org.shirokuma.testData.products.ItemDetail
import org.shirokuma.users.User
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

@Feature("Sauce Demo Tests")
class SauceDemoTest {

    private lateinit var user: User

    @BeforeMethod(alwaysRun = true)
    fun setup() {
        user = User()
        // Check whether the target app have been installed
        assertThat("App need to be installed", user.driver!!.isAppInstalled(DriverManager.appPackage))
        // Make sure that the app start from the login screen
        user.commonSteps.goToLoginScreen()
    }

    @AfterMethod(alwaysRun = true)
    fun cleanUp() {
        // Quit driver
        user.driver?.quit()
    }

    @Test(priority = 1)
    @Story("Do a purchase of 2 items")
    fun `purchase items until checkout`() {
        val itemList = mutableListOf<ItemDetail>()
        user.run {
            loginSteps.inLoginScreen(true)
            loginSteps.doLogin("standard_user", "secret_sauce")
            loginSteps.inLoginScreen(false)
            showcaseSteps.inShowcaseScreen(true)
            headerSteps.isHeaderDisplayed(true)
            repeat(2) {
                val itemNumber = it + 1
                val itemDetail = showcaseSteps.checkItemDetail(itemNumber)
                itemList.add(itemDetail)
                showcaseSteps.clickOnItem(itemNumber)
                showcaseSteps.inShowcaseScreen(false)
                itemSteps.inItemPage(true)
                headerSteps.isHeaderDisplayed(true)
                itemSteps.checkItemDetail(itemDetail)
                itemSteps.clickOnBtnAddToCart()
                itemSteps.clickOnBtnBackToProducts()
                itemSteps.inItemPage(false)
                showcaseSteps.inShowcaseScreen(true)
                headerSteps.isHeaderDisplayed(true)
                showcaseSteps.checkItemDetail(itemNumber, addedToCart = true)
            }
            headerSteps.clickOnCarts()
            showcaseSteps.inShowcaseScreen(false)
            cartSteps.inCartsScreen(true)
            itemList.forEach {
                cartSteps.checkCartItemDetail(it, 1)
            }
            cartSteps.clickOnBtnCheckout()
            cartSteps.inCartsScreen(false)
            checkoutInfoSteps.inCheckoutInfoScreen(true)
            checkoutInfoSteps.fillCheckoutInfo("John", "Smith", "11101")
            checkoutInfoSteps.inCheckoutInfoScreen(false)
            checkoutOverviewSteps.inCheckoutOverviewScreen(true)
            itemList.forEach {
                checkoutOverviewSteps.checkCheckoutItemDetail(it, 1)
            }
            checkoutOverviewSteps.clickOnBtnFinish()
            checkoutOverviewSteps.inCheckoutOverviewScreen(false)
            checkoutCompleteSteps.inCheckoutCompleteScreen(true)
            checkoutCompleteSteps.verifyCheckoutComplete()
        }

    }

}