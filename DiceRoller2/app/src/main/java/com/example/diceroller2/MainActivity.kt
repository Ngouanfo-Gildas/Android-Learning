package com.example.diceroller2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
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
        val resultTextView: TextView = findViewById(R.id.textview)
        resultTextView.text = diceRoll.toString()

        // Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)
        diceImage.setImageResource(R.drawable.dice_2)

        // Determine which drawable resource ID to use based on the dice roll
        when(diceRoll){
            1 -> diceImage.setImageResource(R.drawable.dice_1)
            2 -> diceImage.setImageResource(R.drawable.dice_2)
            3 -> diceImage.setImageResource(R.drawable.dice_3)
            4 -> diceImage.setImageResource(R.drawable.dice_4)
            5 -> diceImage.setImageResource(R.drawable.dice_5)
            6 -> diceImage.setImageResource(R.drawable.dice_6)
        }

        // code refractory
        /** val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        } **/

        // give image description
        diceImage.contentDescription = diceRoll.toString()
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
