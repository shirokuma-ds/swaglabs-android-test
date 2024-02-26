package org.shirokuma.steps.commons

import io.qameta.allure.Step
import org.shirokuma.bases.BaseStep
import org.shirokuma.pages.commons.HeaderComponent
import org.shirokuma.pages.commons.MenuPage
import org.shirokuma.pages.login.LoginPage

class CommonSteps : BaseStep() {

    private val loginPage by lazy { LoginPage() }
    private val headerComponent by lazy { HeaderComponent() }
    private val menuPage by lazy { MenuPage() }

    @Step("[Common] Go to login screen")
    fun goToLoginScreen() {
        if (!loginPage.isScreenDisplayed()) {
            if (!menuPage.isScreenDisplayed()) {
                headerComponent.btnMenu.click()
            }
            menuPage.btnLogout.click()
        }
        softly.assertThat(loginPage.isScreenDisplayed()).isTrue
        softly.assertAll()
    }

}