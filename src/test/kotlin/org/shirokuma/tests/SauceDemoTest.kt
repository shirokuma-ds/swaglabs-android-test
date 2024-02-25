package org.shirokuma.tests

import org.shirokuma.users.User
import org.testng.annotations.Test

class SauceDemoTest {

    @Test
    fun login() {
        val user = User()
        user.run {
            loginSteps.inLoginScreen(true)
            loginSteps.doLogin("standard_user", "secret_sauce")
            loginSteps.inLoginScreen(false)
            showcaseSteps.inShowcaseScreen(true)
            headerSteps.isHeaderDisplayed(true)
            repeat(2) {
                val itemNumber = it + 1
                val itemDetail = showcaseSteps.checkItemDetail(itemNumber)
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
        }
        
    }

}