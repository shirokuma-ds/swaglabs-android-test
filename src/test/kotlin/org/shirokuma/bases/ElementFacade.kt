package org.shirokuma.bases

import org.openqa.selenium.*
import org.shirokuma.drivers.DriverManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ElementFacade : WebElement {

    var locator: By? = null

    private val logger: Logger = LoggerFactory.getLogger(BasePage::class.java)

    companion object {

        /**
         * This function allow user to easily create the element in the Page class
         * Without needs to call the driver every time
         */
        fun create(by: By): ElementFacade {
            val elementFacade = ElementFacade()
            elementFacade.locator = by
            return elementFacade
        }

        fun fromWebElement(element: WebElement): ElementFacade {
            return object : ElementFacade() {
                override fun toElement() = element
                override fun toElements() = mutableListOf(element)
            }
        }

        fun fromWebElements(elements: List<WebElement>): List<ElementFacade> {
            return elements.map {
                fromWebElement(it)
            }
        }
    }

    private val driver = DriverManager.driver

    open fun toElement(): WebElement = driver.findElement(locator)

    open fun toElements(): MutableList<WebElement> = driver.findElements(locator)

    /**
     * Check whether an element is displayed within the screen viewport
     * Even if the element is within the page, but if it is not display in the screen viewport
     * This function will return false
     */
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

    /**
     * This function check 2 condition isDisplayed & isEnabled
     * When calling this function it prevent NoSuchElementException to be thrown
     * Instead it will throw false when an element is not found
     */
    fun exist(): Boolean {
        return try {
            isDisplayed && isEnabled
        } catch (e: NoSuchElementException) {
            false
        }
    }

    override fun findElements(by: By?): MutableList<ElementFacade> =
        fromWebElements(driver.findElements(locator)).toMutableList()

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