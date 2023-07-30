package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var displayText: TextView
    private var currentInput = ""
    private var result = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayText = findViewById(R.id.display_text)
    }

    // Called when a digit button is clicked
    fun onDigitClick(view: View) {
        val button = view as Button
        val digit = button.text.toString()
        currentInput += digit
        updateDisplay()
    }

    // Called when the decimal button is clicked
    fun onDecimalClick(view: View) {
        if (!currentInput.contains(".")) {
            currentInput += "."
            updateDisplay()
        }
    }

    // Called when an operator button is clicked (+, -, *, /)
    fun onOperatorClick(view: View) {
        val button = view as Button
        operator = button.text.toString()
        result = currentInput.toDouble()
        currentInput = ""
        updateDisplay()
    }

    // Called when the equals button is clicked
    fun onEqualsClick(view: View) {
        if (!operator.isEmpty() && !currentInput.isEmpty()) {
            val secondOperand = currentInput.toDouble()
            when (operator) {
                "+" -> result += secondOperand
                "-" -> result -= secondOperand
                "*" -> result *= secondOperand
                "/" -> {
                    if (secondOperand != 0.0)
                        result /= secondOperand
                    else
                        currentInput = "Error"
                }
            }
            operator = ""
            currentInput = DecimalFormat("#.##########").format(result)
            updateDisplay()
        }
    }

    // Called when the clear button is clicked
    fun onClearClick(view: View) {
        currentInput = ""
        result = 0.0
        operator = ""
        updateDisplay()
    }

    // Update the display with the current input
    private fun updateDisplay() {
        displayText.text = currentInput
    }
}
