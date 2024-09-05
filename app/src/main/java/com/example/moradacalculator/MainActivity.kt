package com.example.moradacalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var operand1EditText: EditText? = null
    private var operand2EditText: EditText? = null
    private var resultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operand1EditText = findViewById(R.id.operand1)
        operand2EditText = findViewById(R.id.operand2)
        resultTextView = findViewById(R.id.output)

        val addButton = findViewById<Button>(R.id.add)
        val subtractButton = findViewById<Button>(R.id.subtract)
        val multiplyButton = findViewById<Button>(R.id.multiply)
        val divideButton = findViewById<Button>(R.id.divide)
        val clearButton = findViewById<Button>(R.id.clear)

        addButton.setOnClickListener { performOperation(Operation.ADD) }

        subtractButton.setOnClickListener { performOperation(Operation.SUBTRACT) }

        multiplyButton.setOnClickListener { performOperation(Operation.MULTIPLY) }

        divideButton.setOnClickListener { performOperation(Operation.DIVIDE) }

        clearButton.setOnClickListener { clearInputs() }
    }

    private fun performOperation(operation: Operation) {
        val operand1Str = operand1EditText!!.text.toString()
        val operand2Str = operand2EditText!!.text.toString()

        if (operand1Str.isEmpty() || operand2Str.isEmpty()) {
            resultTextView!!.text = "Please enter both operands"
            return
        }

        val operand1 = operand1Str.toDouble()
        val operand2 = operand2Str.toDouble()
        var result = 0.0

        when (operation) {
            Operation.ADD -> result = operand1 + operand2
            Operation.SUBTRACT -> result = operand1 - operand2
            Operation.MULTIPLY -> result = operand1 * operand2
            Operation.DIVIDE -> if (operand2 != 0.0) {
                result = operand1 / operand2
            } else {
                resultTextView!!.text = "Cannot divide by zero"
                return
            }
        }
        resultTextView!!.text = result.toString()
    }

    private fun clearInputs() {
        operand1EditText!!.setText("")
        operand2EditText!!.setText("")
        resultTextView!!.text = "Result"
    }

    private enum class Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }
}

