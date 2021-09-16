package hu.ait.simplecalculator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.simplecalculator.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlus.setOnClickListener {
            binding.tvResult.text = ""

            try {
                if (binding.etNumA.text.isNotEmpty()){
                    if (binding.etNumB.text.isNotEmpty()) {
                        val numA = binding.etNumA.text.toString().toInt()
                        val numB = binding.etNumB.text.toString().toInt()
                        val result = numA + numB

                        binding.tvResult.text = "Result: $result"
                    } else {
                        binding.etNumB.error = "This can not be empty"
                    }
                } else {
                    binding.etNumA.error = "This can not be empty"
                }

            } catch (e: Exception) {
                binding.tvResult.text = e.message
            }
        }
    }
}