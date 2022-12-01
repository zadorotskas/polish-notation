package ru.spbstu.icc.kspt.calculation

object Calculator {
    fun calculatePolishExpression(expression: List<String>): Int {
        val stack = ArrayDeque<Int>()

        expression.forEach {
            when (it) {
                "*" -> {
                    val res: Int = stack.removeLast() * stack.removeLast()
                    stack.add(res)
                }

                "+" -> {
                    val res: Int = stack.removeLast() + stack.removeLast()
                    stack.add(res)
                }

                "-" -> {
                    val deductible: Int = stack.removeLast()
                    val numToBeReduced: Int = stack.removeLast()
                    val res = numToBeReduced - deductible
                    stack.add(res)
                }

                "/" -> {
                    val divider: Int = stack.removeLast()
                    val divisible: Int = stack.removeLast()
                    val res = divisible / divider
                    stack.add(res)
                }

                else -> {
                    val number = it.toInt()
                    stack.add(number)
                }
            }
        }

        return stack.last()
    }
    
}