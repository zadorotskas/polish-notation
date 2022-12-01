package ru.spbstu.icc.kspt.converters

import java.util.*

object ConverterFromPolishImpl : Converter {
    override fun convert(input: String): String {
        return fromPolish(input.split(" ").toMutableList())
    }

    private fun fromPolish(polish: MutableList<String>): String {
        var n = 0
        while (polish.size > 1) {
            when (polish[n]) {
                "+", "-", "*", "/" -> {
                    val s = StringJoiner("", "(", ")").add(polish.removeAt(n - 2)).add(polish.removeAt(n - 1)).add(polish.removeAt(n - 2)).toString()
                    polish.add(n - 2, s)

                    return if (polish.size > 1) fromPolish(polish) else polish[0]
                }
            }
            n++
        }
        return fromPolish(polish)
    }
}