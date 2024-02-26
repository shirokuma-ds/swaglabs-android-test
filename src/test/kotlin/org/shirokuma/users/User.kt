package org.shirokuma.users

import org.openqa.selenium.remote.DesiredCapabilities
import org.shirokuma.bases.BaseUser
import org.shirokuma.steps.checkouts.CartsSteps
import org.shirokuma.steps.checkouts.CheckoutCompleteSteps
import org.shirokuma.steps.checkouts.CheckoutInfoSteps
import org.shirokuma.steps.checkouts.CheckoutOverviewSteps
import org.shirokuma.steps.commons.CommonSteps
import org.shirokuma.steps.commons.HeaderSteps
import org.shirokuma.steps.items.ItemSteps
import org.shirokuma.steps.items.ShowcaseSteps
import org.shirokuma.steps.login.LoginSteps

class User(capabilities: DesiredCapabilities? = null) : BaseUser(capabilities) {
    val commonSteps by lazy { CommonSteps() }
    val loginSteps by lazy { LoginSteps() }
    val headerSteps by lazy { HeaderSteps() }
    val showcaseSteps by lazy { ShowcaseSteps() }
    val itemSteps by lazy { ItemSteps() }
    val cartSteps by lazy { CartsSteps() }
    val checkoutInfoSteps by lazy { CheckoutInfoSteps() }
    val checkoutOverviewSteps by lazy { CheckoutOverviewSteps() }
    val checkoutCompleteSteps by lazy { CheckoutCompleteSteps() }
}