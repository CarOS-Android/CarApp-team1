package com.thoughtworks.car.core.logging.printer

import com.thoughtworks.car.core.logging.printer.decoration.Decoration

class ConsolePrinter(decorations: List<Decoration> = emptyList()) : Printer(decorations) {
    override fun printLine(priority: Int, tag: String?, message: String) {
        println("$tag $message")
    }
}
