package ru.spbstu.icc.kspt.validation

object ValidatorPolishImpl : Validator {
    override fun isValid(expression: String): Boolean {
        var stackSize = 0
        expression.trim().split(" ").forEach {
            if (it.isOperator()) {
                stackSize--
            } else if (it.isNumber()) {
                stackSize++
            } else {
                return false
            }
        }
        return stackSize == 1
    }

    private fun String.isOperator(): Boolean {
        return this == "*" || this == "/" || this == "+" || this == "-"
    }

    private fun String.isNumber(): Boolean {
        return this.all {
            it.code - '0'.code in 0..9
        }
    }
}
