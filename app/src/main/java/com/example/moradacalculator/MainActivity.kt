package com.example.moradacalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var operand1: Double? = null
    private var operator: String? = null
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val input: TextView = findViewById(R.id.input)
        val output: TextView = findViewById(R.id.total)

        val buttons = mapOf(
            R.id.Num1 to "1",
            R.id.Num2 to "2",
            R.id.Num3 to "3",
            R.id.Num4 to "4",
            R.id.Num5 to "5",
            R.id.Num6 to "6",
            R.id.Num7 to "7",
            R.id.Num8 to "8",
            R.id.Num9 to "9",
            R.id.zero to "0",
            R.id.doublezero to "00",
            R.id.addition to "+",
            R.id.subtract to "-",
            R.id.multiply to "*",
            R.id.divide to "/",
            R.id.clearall to "C",
            R.id.clearone to "CE"
        )

        buttons.forEach { (id, value) ->
            findViewById<Button>(id).setOnClickListener {
                when (value) {
                    "C" -> {
                        input.text = ""
                        output.text = ""
                        operand1 = null
                        operator = null
                        isNewOperation = true
                    }
                    "CE" -> {
                        if (input.text.isNotEmpty()) {
                            input.text = input.text.dropLast(1)
                        }
                    }
                    "+", "-", "*", "/" -> {
                        if (input.text.isNotEmpty()) {
                            operand1 = input.text.toString().toDouble()
                            operator = value
                            input.text = ""
                            isNewOperation = false
                        }
                    }
                    else -> {
                        if (isNewOperation) {
                            input.text = value
                            isNewOperation = false
                        } else {
                            input.text = "${input.text}$value"
                        }
                    }
                }
            }
        }

        findViewById<Button>(R.id.equals).setOnClickListener {
            if (input.text.isNotEmpty() && operand1 != null && operator != null) {
                val operand2 = input.text.toString().toDouble()
                val result = when (operator) {
                    "+" -> operand1!! + operand2
                    "-" -> operand1!! - operand2
                    "*" -> operand1!! * operand2
                    "/" -> if (operand2 != 0.0) operand1!! / operand2 else Double.NaN
                    else -> Double.NaN
                }
                output.text = result.toString().take(10)  // Display result with up to 10 characters
                operand1 = null
                operator = null
                isNewOperation = true
            }
        }
    }
}
