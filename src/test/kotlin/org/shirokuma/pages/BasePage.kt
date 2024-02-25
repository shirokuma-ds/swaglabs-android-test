package org.shirokuma.pages

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.WebDriverWait
import org.shirokuma.drivers.DriverManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

abstract class BasePage {
    protected val driver = DriverManager.driver
    protected val logger: Logger = LoggerFactory.getLogger(BasePage::class.java)


    fun create(by: By): ElementFacade = ElementFacade.create(by)

    abstract fun trait(): ElementFacade

    fun isScreenDisplayed(expected: Boolean = true, duration: Duration = Duration.ofSeconds(2)): Boolean {
        val wait = WebDriverWait(driver, duration)
        return try {
            wait.until { trait().isDisplayed == expected }
            trait().isDisplayed
        } catch (e: TimeoutException) {
            false
        } catch (e: NoSuchElementException) {
            false
        }
    }

    fun isDisplayedOnScreen(element: WebElement): Boolean {
        val screenSize = driver.manage().window().size
        val screenPosition = driver.manage().window().position

        val isDisplayed = try {
            element.isDisplayed
        } catch (e: NoSuchElementException) {
            return false
        }

        val elSize = element.size
        val elPos = element.location

        return isDisplayed
                && screenPosition.x <= elPos.x
                && screenPosition.y <= elPos.y
                && screenPosition.x + screenSize.width >= elPos.x + elSize.width
                && screenPosition.y + screenSize.height >= elPos.y + elSize.height
    }
}