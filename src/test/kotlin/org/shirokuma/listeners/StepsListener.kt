package org.shirokuma.listeners

import io.qameta.allure.Allure
import io.qameta.allure.listener.StepLifecycleListener
import io.qameta.allure.model.StepResult
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.shirokuma.drivers.DriverManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream

class StepsListener : StepLifecycleListener {

    private val logger: Logger = LoggerFactory.getLogger(StepsListener::class.java)

    private val takesScreenshot by lazy { DriverManager.driver as TakesScreenshot }

    private fun getScreenshotInputStream(): ByteArrayInputStream {
        val screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES)
        return ByteArrayInputStream(screenshot)
    }

    override fun afterStepStart(result: StepResult?) {
        logger.info("Begin executing step ${result?.name}")
        Allure.addAttachment("Screen before step ${result?.name}", getScreenshotInputStream())
    }

    override fun beforeStepStop(result: StepResult?) {
        logger.info("Finish executing step ${result?.name}")
        Allure.addAttachment("Screen after step ${result?.name}", getScreenshotInputStream())
    }

}