package org.shirokuma.steps

import io.qameta.allure.Step
import org.shirokuma.pages.LoginPage
import java.time.Duration
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class LoginSteps {

    private val loginPage by lazy { LoginPage() }

    @Step("[Login] User is in login screen is {expected}")
    fun inLoginScreen(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        assertThat(loginPage.isScreenDisplayed(expected, duration), `is`(expected))
    }

    @Step("[Login] Do login with username {username} and password {password}")
    fun doLogin(username: String, password: String) {
        loginPage.run {
            fieldUsername.sendKeys(username)
            fieldPassword.sendKeys(password)
            btnLogin.click()
        }
    }

}