package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /* on stock l'ID du bouton dans la variable rollButton */
        val rollButton: Button = findViewById(R.id.bt_name)
        rollButton.setOnClickListener() {
            val toast = Toast.makeText(this, "Dices rolled..", Toast.LENGTH_SHORT).show()
            rollDice()
        }
    }

    /**
     * This private function create new dice and roll it
     */
    private fun rollDice() {
        // create a new dice object width 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()
        //update the result of roll to screen
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()

        // create a new dice object width 6 sides and roll it
        val dice1 = Dice(6)
        val dice1Roll = dice1.roll()
        //update the result of roll to screen
        val result1TextView: TextView = findViewById(R.id.textView1)
        result1TextView.text = dice1Roll.toString()

    }
}

/**
 *  définition de la classe Dice avec la fonction roll()
 */

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
