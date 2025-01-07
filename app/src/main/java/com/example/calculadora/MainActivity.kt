package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.R

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var current = ""
    private var result = 0.0
    private var operation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                current += (it as Button).text
                display.text = current
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            current = ""
            result = 0.0
            operation = ""
            display.text = "0"
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperation("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperation("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperation("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperation("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            val num = current.toDoubleOrNull()
            if (num != null) {
                result = when (operation) {
                    "+" -> result + num
                    "-" -> result - num
                    "*" -> result * num
                    "/" -> if (num != 0.0) result / num else Double.NaN
                    else -> num
                }
                display.text = result.toString()
                current = ""
            }
        }
    }

    private fun setOperation(op: String) {
        result = current.toDoubleOrNull() ?: 0.0
        operation = op
        current = ""
    }
}