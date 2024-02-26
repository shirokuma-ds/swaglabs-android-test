package org.shirokuma.bases

import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.support.ui.WebDriverWait
import org.shirokuma.drivers.DriverManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration

abstract class BasePage {
    protected val driver = DriverManager.driver
    protected val logger: Logger = LoggerFactory.getLogger(BasePage::class.java)

    // Wrap the ElementFacade.create, so when Page class extends this BasePage class, it does not need to call ElementFacade class
    fun create(by: By): ElementFacade = ElementFacade.create(by)

    /**
     * Trait act as unique identifier of the page
     * This function must be overriden by the child class
     */
    abstract fun trait(): ElementFacade

    /**
     * Function to check whether this page is displayed
     * @param expected whether this page is expected to be displayed or not
     * @param duration duration of wait until the unique trait of the page is displayed, default is 2 seconds
     * @return boolean whether the page is displayed
     */
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
}