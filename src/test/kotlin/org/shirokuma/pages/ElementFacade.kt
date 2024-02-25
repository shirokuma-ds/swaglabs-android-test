package org.shirokuma.pages

import org.openqa.selenium.*
import org.shirokuma.drivers.DriverManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ElementFacade(val locator: By) : WebElement {

    private val logger: Logger = LoggerFactory.getLogger(BasePage::class.java)

    companion object {
        fun create(by: By): ElementFacade = ElementFacade(by)
    }

    private val driver = DriverManager.driver
    
    fun toElement(): WebElement = driver.findElement(locator)

    fun toElements(): MutableList<WebElement> = driver.findElements(locator)

    fun isDisplayedOnScreen(): Boolean {
        val screenSize = driver.manage().window().size
        val screenPosition = driver.manage().window().position

        val isDisplayed = try {
            isDisplayed
        } catch (e: NoSuchElementException) {
            return false
        }

        val elSize = size
        val elPos = location

        return isDisplayed
                && screenPosition.x <= elPos.x
                && screenPosition.y <= elPos.y
                && screenPosition.x + screenSize.width >= elPos.x + elSize.width
                && screenPosition.y + screenSize.height >= elPos.y + elSize.height
    }

    fun exist(): Boolean {
        return try {
            isDisplayed && isEnabled
        } catch (e: NoSuchElementException) {
            false
        }
    }
    
    override fun findElements(by: By?): MutableList<WebElement> = driver.findElements(locator)

    override fun findElement(by: By?): ElementFacade = create(by!!)

    override fun <X : Any?> getScreenshotAs(target: OutputType<X>?): X = driver.getScreenshotAs(target)

    override fun click() {
        toElement().click()
    }

    override fun submit() {
        toElement().submit()
    }

    override fun sendKeys(vararg keysToSend: CharSequence?) {
        toElement().sendKeys(*keysToSend)
    }

    override fun clear() {
        toElement().clear()
    }

    override fun getTagName(): String = toElement().tagName


    override fun getAttribute(name: String?): String = toElement().getAttribute(name)

    override fun isSelected(): Boolean = toElement().isSelected

    override fun isEnabled(): Boolean = toElement().isEnabled

    override fun getText(): String = toElement().text

    override fun isDisplayed(): Boolean = toElement().isDisplayed

    override fun getLocation(): Point = toElement().location

    override fun getSize(): Dimension = toElement().size

    override fun getRect(): Rectangle = toElement().rect

    override fun getCssValue(propertyName: String?): String = toElement().getCssValue(propertyName)
}