package com.example.ts_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateDisplay("")
    }

    private val operationList: MutableList<String> = arrayListOf()
    private val numberCache: MutableList<String> = arrayListOf()


    private fun makeString(list: List<String>, joiner: String = ""): String {
        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

    private fun clearCache() {
        numberCache.clear()
        operationList.clear()
    }

    private fun updateDisplay(mainDisplayString: String) {

        val fullCalculationString = makeString(operationList, " ")
        var fullCalculationTextView = findViewById<TextView>(R.id.fullCalculationText)
        fullCalculationTextView.text = fullCalculationString

        val mainTextView = findViewById<TextView>(R.id.textView)
        mainTextView.text = mainDisplayString
    }

    fun clearClick(view: View) {
        clearCache()
        updateDisplay("")
    }

    fun equalsClick(view: View) {
        operationList.add(makeString(numberCache))
        numberCache.clear()

        val calculator = StringCalculator()
        val answer = calculator.calculate(operationList)
        updateDisplay("" + answer.toString())
        clearCache()
    }


    fun dotClick(view: View) {
        val button = view as Button
        numberCache.add(".")
        val text = makeString(numberCache)
        updateDisplay(text)
    }

    fun buttonClick(view: View) {

        val button = view as Button

        if (numberCache.isEmpty()) return
        operationList.add(makeString(numberCache))
        numberCache.clear()
        operationList.add(button.text.toString())

        updateDisplay("")
    }

    fun numberClick(view: View) {
        val button = view as Button
        val numberString = button.text

        numberCache.add(numberString.toString())
        val text = makeString(numberCache)
        updateDisplay(text)
    }
}


