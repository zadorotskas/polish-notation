package ru.spbstu.icc.kspt.converters

import java.util.*

object ConverterToReversePolishImpl : Converter {
    override fun convert(input: String): String {
        var res = ""
        val len = input.length
        val operator = Stack<Char>()
        val reversePolish = Stack<String>()

        operator.push('#')
        var i = 0
        while (i < len) {

            while (i < len && input[i] == ' ') {
                i++
            }
            if (i == len) break

            if (isNum(input[i])) {
                var num = ""
                while (i < len && isNum(input[i])) {
                    num += input[i++]
                }
                reversePolish.push(num)

            } else if (isOperator(input[i])) {
                when (val op = input[i]) {
                    '(' -> operator.push(op)
                    ')' -> {
                        while (operator.peek() != '(') {
                            reversePolish.push(operator.pop().toString())
                        }
                        operator.pop()
                    }

                    '+', '-' -> if (operator.peek() == '(') operator.push(op) else {
                        while (operator.peek() != '#' && operator.peek() != '(') {
                            reversePolish.push(operator.pop().toString())
                        }
                        operator.push(op)
                    }

                    '*', '/' -> if (operator.peek() == '(') operator.push(op) else {
                        while (operator.peek() != '#' && operator.peek() != '+'
                            && operator.peek() != '-' && operator.peek() != '('
                        ) {
                            reversePolish.push(operator.pop().toString())
                        }
                        operator.push(op)
                    }
                }
                i++
            }
        }
        while (operator.peek() != '#') {
            reversePolish.push(operator.pop().toString())
        }
        while (!reversePolish.isEmpty()) {
            res = if (res.isEmpty()) {
                reversePolish.pop() + res
            } else {
                reversePolish.pop() + " " + res
            }
        }
        return res
    }

    private fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
    }

    private fun isNum(c: Char): Boolean {
        return c.code - '0'.code in 0..9
    }
}
