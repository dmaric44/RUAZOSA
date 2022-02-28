package lecture4.ruazosa.fer.hr.calculator

import org.junit.Assert
import org.junit.Test

/**
 * Created by dejannovak on 25/03/2018.
 */
class CalculatorUnitTest {
    @Test
    fun test_reset() {
        Calculator.reset()
        Assert.assertEquals(0.0, Calculator.result, 0.0)
        Assert.assertEquals(0, Calculator.expression.count())
    }

    @Test
    fun test_addNumber() {
        Calculator.reset()
        try {
            Calculator.addNumber("Not a number")
            Assert.fail()
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not valid number")
        }
        Calculator.reset()

        try {
            Calculator.addNumber("100.00")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid order of numbers in expression")
        }
        Calculator.reset()

        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }

    }

    @Test
    fun test_addOperator() {
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("*")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid operator")
        }

        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.fail()
        }
        Calculator.reset()
        try {
            Calculator.addNumber("100.00")
            Calculator.addOperator("+")
            Calculator.addOperator("-")
            Calculator.addNumber("200.00")
        }
        catch (exc: Exception) {
            Assert.assertEquals(exc.message, "Not a valid order of operator in expression")
        }
    }

    @Test
    fun test_evaluate() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("+")
        Calculator.addNumber("200")
        Calculator.addOperator("-")
        Calculator.addNumber("300")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 0.00, 0.00)
    }

    @Test
    fun test_multiplication() {
        Calculator.reset()
        Calculator.addNumber("10")
        Calculator.addOperator("*")
        Calculator.addNumber("20")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 200.00, 0.00)
    }

    @Test
    fun test_division() {
        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("/")
        Calculator.addNumber("50")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 2.00, 0.00)

        Calculator.reset()
        Calculator.addNumber("100")
        Calculator.addOperator("/")
        Calculator.addNumber("0")
        try {
            Calculator.evaluate()
        }catch (exc: Exception){
            Assert.assertEquals(exc.message, "Division by zero is not allowed!")
        }
    }

    @Test
    fun test_operations_order() {
        Calculator.reset()
        Calculator.addNumber("10")
        Calculator.addOperator("+")
        Calculator.addNumber("12")
        Calculator.addOperator("/")
        Calculator.addNumber("2")
        Calculator.addOperator("*")
        Calculator.addNumber("6")
        Calculator.addOperator("-")
        Calculator.addNumber("10")
        Calculator.addOperator("/")
        Calculator.addNumber("5")
        Calculator.evaluate()
        Assert.assertEquals(Calculator.result, 44.00, 0.00)
    }
}