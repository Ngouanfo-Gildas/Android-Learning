package com.example.tiptime


import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
} */

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    private fun calculateTip(){
        // take the textField value and convert to string
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        // next we convert to double
        val cost = stringInTextField.toDoubleOrNull() //return double or null

        if (cost == null) {
            binding.tipResult.text = ""
            return    // quitter la fonction sans exécuter le code suivant
        }

        // take id of checked button
        val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        // we calculate tip value
        var tip = tipPercentage * cost

        val roundUp: Boolean = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        var totalCost = cost + tip

        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        val formattedTotalCost = NumberFormat.getCurrencyInstance().format(totalCost)
        //binding.totalAmount.text = getString(R.string.total_amount, formattedTotalCost)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}