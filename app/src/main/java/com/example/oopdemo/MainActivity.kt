package com.example.oopdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.etWeight)
        val height = findViewById<EditText>(R.id.etHeight)
        val btnCalc = findViewById<Button>(R.id.btnCalc)

        btnCalc.setOnClickListener {
            val wText = weight.text.toString()
            val hText = height.text.toString()

            if (validateInput(wText, hText)) {
                val wFloat = wText.toFloat()
                val hFloat = hText.toFloat()

                val bmi = wFloat / ((hFloat / 100) * (hFloat / 100))
                val bmi2d = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2d)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    private fun displayResult(bmi:Float) {
        val resultIdx = findViewById<TextView>(R.id.tvIndex)
        val resultDesc = findViewById<TextView>(R.id.tvResult)
        val resultInfo = findViewById<TextView>(R.id.tvInfo)

        resultIdx.text = bmi.toString()
        resultInfo.text = "(Normal range is 18.5 - 24.9)"

        var text = ""
        var color = 0

        when {
            bmi < 18.50 -> {
                text = "Underweight"
                color = R.color.underweight
            }
            bmi in 18.50..24.99 -> {
                text = "Healthy"
                color = R.color.healthy
            }
            bmi in 25.00..29.99 -> {
                text = "Overweight"
                color = R.color.overweight
            }
            bmi > 29.99 -> {
                text = "Obese"
                color = R.color.obese
            }
        }

        resultDesc.setTextColor(ContextCompat.getColor(this, color))
        resultDesc.text = text
    }
}