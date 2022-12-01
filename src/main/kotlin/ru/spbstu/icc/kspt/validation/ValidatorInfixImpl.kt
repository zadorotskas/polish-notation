package ru.spbstu.icc.kspt.validation

import java.util.regex.Matcher
import java.util.regex.Pattern

object ValidatorInfixImpl : Validator {
    private var simpleLang: Pattern = Pattern.compile("\\s*-?\\d+(\\s*[-+*/]\\s*-?\\d+)*\\s*")
    private var innerParen: Pattern = Pattern.compile("[(]([^()]*)[)]")

    override fun isValid(expression: String): Boolean {
        var expr = expression
        while (expr.contains(")") || expr.contains("(")) {
            val m: Matcher = innerParen.matcher(expr)
            expr = if (m.find()) {
                if (!simpleLang.matcher(m.group(1)).matches()) {
                    return false
                }
                expr.substring(0, m.start()) + " 1 " + expr.substring(m.end())
            } else {
                return false
            }
        }
        return simpleLang.matcher(expr).matches()
    }
}
