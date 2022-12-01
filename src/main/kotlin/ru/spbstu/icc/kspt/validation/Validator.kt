package ru.spbstu.icc.kspt.validation

interface Validator {
    fun isValid(expression: String): Boolean
}