package org.shirokuma.users

import org.openqa.selenium.remote.DesiredCapabilities
import org.shirokuma.steps.HeaderSteps
import org.shirokuma.steps.ItemSteps
import org.shirokuma.steps.LoginSteps
import org.shirokuma.steps.ShowcaseSteps

class User(capabilities: DesiredCapabilities? = null): BaseUser(capabilities) {
    val loginSteps by lazy { LoginSteps() }
    val headerSteps by lazy { HeaderSteps() }
    val showcaseSteps by lazy { ShowcaseSteps() }
    val itemSteps by lazy { ItemSteps() }
}