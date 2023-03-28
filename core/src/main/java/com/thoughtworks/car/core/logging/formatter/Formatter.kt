package com.thoughtworks.car.core.logging.formatter

interface Formatter {

    /**
     * Format the [message] with [args]
     */
    fun format(message: String, vararg args: Any?): String
}
