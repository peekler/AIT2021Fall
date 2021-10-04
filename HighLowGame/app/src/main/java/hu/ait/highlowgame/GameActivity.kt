package hu.ait.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.highlowgame.databinding.ActivityGameBinding
import java.util.*

class GameActivity : AppCompatActivity() {

    var generatedNum = -1
    lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if ((savedInstanceState != null) && savedInstanceState.containsKey("KEY_GEN")){
            generatedNum = savedInstanceState.getInt("KEY_GEN")
        } else {
            generateNewRandomNumber()
        }

        binding.btnGuess.setOnClickListener {
            try {
                if (binding.etNumber.text!!.isNotEmpty()) {

                    val myNumber = binding.etNumber.text.toString().toInt()

                    if (myNumber < generatedNum) {
                        binding.tvResult.text = "The number is higher"
                    } else if (myNumber > generatedNum) {
                        binding.tvResult.text = "The number is lower"
                    } else if (myNumber == generatedNum) {
                        binding.tvResult.text = "Congratulations! You have won!"

                        startActivity(Intent(this, ResultActivity::class.java))
                    }
                } else {
                    binding.etNumber.error = "This field can not be empty"
                }
            } catch (e: Exception) {
                binding.tvResult.text = "Error ${e.message}"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("KEY_GEN", generatedNum)
    }


    fun generateNewRandomNumber() {
        val rand = Random(System.currentTimeMillis())
        generatedNum = rand.nextInt(3)
    }
}