package org.shirokuma.steps

import io.qameta.allure.Step
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.shirokuma.pages.components.HeaderComponent
import java.time.Duration

class HeaderSteps : BaseStep() {
    private val headerComponent by lazy { HeaderComponent() }

    @Step("[Header] Header is displayed on screen is {expected}")
    fun isHeaderDisplayed(expected: Boolean, duration: Duration = Duration.ofSeconds(2)) {
        MatcherAssert.assertThat(headerComponent.isScreenDisplayed(expected, duration), Matchers.`is`(expected))
    }
}