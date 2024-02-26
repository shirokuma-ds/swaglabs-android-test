package org.shirokuma.utils

import org.openqa.selenium.interactions.Actions
import org.shirokuma.bases.ElementFacade
import org.shirokuma.drivers.DriverManager

object Swipe {

    /**
     * Perform swipe on the screen
     * @param swipeDirection the direction of the swipe that will be performed
     */
    fun swipe(swipeDirection: SwipeDirection) {
        val driver = DriverManager.driver
        val actions = Actions(driver)

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

    /**
     * Perform swipe until the element is displayed on the screen
     * @param element target element that want to be found
     * @param swipeDirection direction of the swipe
     * @param maxAttempt number of swipe attempt before the swipe stop, default: 10
     */
    fun swipeIntoView(element: ElementFacade, swipeDirection: SwipeDirection, maxAttempt: Int = 10) {
        var attempt = 0
        while (!element.isDisplayedOnScreen() && attempt++ < maxAttempt) {
            swipe(swipeDirection)
        }
    }

}

enum class SwipeDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT
}