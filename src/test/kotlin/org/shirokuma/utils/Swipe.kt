package org.shirokuma.utils

import org.openqa.selenium.interactions.Actions
import org.shirokuma.drivers.DriverManager
import org.shirokuma.pages.ElementFacade

object Swipe {

    fun swipeIntoView(element: ElementFacade, swipeDirection: SwipeDirection, maxAttempt: Int = 10) {
        val driver = DriverManager.driver
        val actions = Actions(driver)
        var attempt = 0
        while (!element.isDisplayedOnScreen() && attempt++ < maxAttempt) {
            val pos = driver.manage().window().position
            val size = driver.manage().window().size
            val startX = pos.x + (size.width / 2)
            val startY = pos.y + (size.height / 2)
            when (swipeDirection) {
                SwipeDirection.UP -> {
                    val targetY = pos.y + (size.height / 4)
                    actions.moveToLocation(startX, startY)
                        .clickAndHold()
                        .moveToLocation(startX, targetY)
                        .release()
                        .perform()
                }
                SwipeDirection.DOWN -> {
                    val targetY = pos.y + size.height
                    actions.moveToLocation(startX, startY)
                        .clickAndHold()
                        .moveToLocation(startX, targetY)
                        .release()
                        .perform()
                }
                SwipeDirection.LEFT -> {
                    val targetX = pos.x
                    actions.moveToLocation(startX, startY)
                        .clickAndHold()
                        .moveToLocation(targetX, startY)
                        .release()
                        .perform()
                }
                SwipeDirection.RIGHT -> {
                    val targetX = pos.x + size.width
                    actions.moveToLocation(startX, startY)
                        .clickAndHold()
                        .moveToLocation(targetX, startY)
                        .release()
                        .perform()
                }
            }
        }
    }

}

enum class SwipeDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT
}