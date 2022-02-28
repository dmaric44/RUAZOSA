package lecture4.ruazosa.fer.hr.calculator

import java.lang.ArithmeticException
import java.util.*
import kotlin.math.exp

/**
 * Created by dejannovak on 24/03/2018.
 */
object Calculator {

    var result: Double = 0.0
        private set

    var expression: MutableList<String> = mutableListOf()
        private set

    var stack = Stack<String>()
        private set

    fun reset() {
        result = 0.0
        expression = mutableListOf()
        stack.clear()
    }

    fun addNumber(number: String) {
        try {
            val num = number.toDouble()
        } catch (e: NumberFormatException) {
            throw Exception("Not valid number")
        }

        if (expression.count()%2 == 0) {
            expression.add(number)
        }
        else {
            throw Exception("Not a valid order of numbers in expression")
        }
    }

    fun addOperator(operator: String) {
        if (expression.count()%2 != 1)  {
            throw Exception("Not a valid order of operator in expression")
        }
        when (operator) {
            "+" -> expression.add(operator)
            "-" -> expression.add(operator)
            "*" -> expression.add(operator)
            "/" -> expression.add(operator)
            else -> {
                throw Exception("Not a valid operator")
            }
        }
    }

    fun evaluate() {

        if (expression.count() % 2 == 0) {
            throw Exception("Not a valid expression")
        }

        var i = 0
        while (i < expression.count()) {
            if (expression[i] == "*") {
                result = stack.pop().toDouble() * expression[i + 1].toDouble()
                i += 1
                stack.push(result.toString())
            } else if (expression[i] == "/") {
                if(expression[i+1] == "0"){
                    reset()
                    throw ArithmeticException("Division by zero is not allowed!")
                }
                result = stack.pop().toDouble() / expression[i + 1].toDouble()
                i += 1
                stack.push(result.toString())
            } else {
                stack.push(expression[i])
            }
            i += 1
        }

        var currentResult = stack.pop().toDouble()

        while (!stack.isEmpty()) {
            val operator = stack.pop()
            val number = stack.pop().toDouble()


            when (operator) {
                "+" -> currentResult = number + currentResult
                "-" -> currentResult = number - currentResult
            }
        }
        result = currentResult




//        result = expression[0].toDouble()
//
//        for(i in 1..expression.count()- 1 step 2) {
//            when(expression[i]) {
//                "+" -> result = result + expression[i+1].toDouble()
//                "-" -> result = result - expression[i+1].toDouble()
//            }
//        }
    }
}