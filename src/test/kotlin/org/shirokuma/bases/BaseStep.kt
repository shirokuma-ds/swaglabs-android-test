package org.shirokuma.bases

import org.assertj.core.api.SoftAssertions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class BaseStep {
    protected val logger: Logger = LoggerFactory.getLogger(BaseStep::class.java)
    protected val softly by lazy { SoftAssertions() }
}