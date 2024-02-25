package org.shirokuma.listeners

import io.qameta.allure.listener.TestLifecycleListener
import io.qameta.allure.model.TestResult

class TestsListener : TestLifecycleListener {

    override fun beforeTestStart(result: TestResult?) {
        println("==========================================")
        println("START TEST")
        println("Test Name: ${result?.name}")
        println("Test Path: ${result?.fullName}")
        println("==========================================")
    }

    override fun afterTestStop(result: TestResult?) {
        println("==========================================")
        println("TEST FINISHED")
        println("Test Name: ${result?.name}")
        println("Test Path: ${result?.fullName}")
        println("Test Result: ${result?.status?.name}")
        println("==========================================")
    }
}