package com.marwendevs.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvResult : TextView? =null

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tvResult)

    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvResult?.text.toString()
            var prefix = ""
            try {
                //check if the value start with "-" if not skip the block of code
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    //example if the value is -100 the result will be 100
                    //note: substring removes the starting index
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var numOne = splitValue[0]
                    val numTwo = splitValue[1]

                    if (prefix.isNotEmpty()){
                        numOne = prefix + numOne
                    }
                    tvResult?.text = removeZeroAfterDot((numOne.toDouble() - numTwo.toDouble()).toString())
                }
                if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var numOne = splitValue[0]
                    val numTwo = splitValue[1]

                    if (prefix.isNotEmpty()){
                        numOne = prefix + numOne
                    }
                    tvResult?.text = removeZeroAfterDot((numOne.toDouble() + numTwo.toDouble()).toString())
                }
                if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var numOne = splitValue[0]
                    val numTwo = splitValue[1]

                    if (prefix.isNotEmpty()){
                        numOne = prefix + numOne
                    }
                    tvResult?.text = removeZeroAfterDot((numOne.toDouble() / numTwo.toDouble()).toString())
                }
                if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var numOne = splitValue[0]
                    val numTwo = splitValue[1]

                    if (prefix.isNotEmpty()){
                        numOne = prefix + numOne
                    }
                    tvResult?.text = removeZeroAfterDot((numOne.toDouble() * numTwo.toDouble()).toString())
                }



            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }


    private fun removeZeroAfterDot(result: String):String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length -2)
        }
return value
    }

    fun onDigit(view: View) {
        tvResult?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }


    fun onClear(view: View) {
        tvResult?.text = ""
    }
    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvResult?.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun  onOperator(view: View){

        //if its not empty the it let run  the block of code
        tvResult?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvResult?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

   private fun isOperatorAdded(value: String) :Boolean{
        return if (value.startsWith("-")){
            false
        } else {
                    value.contains("/")
                    ||value.contains("+")
                    ||value.contains("*")
                    ||value.contains("-")
        }
    }

}

