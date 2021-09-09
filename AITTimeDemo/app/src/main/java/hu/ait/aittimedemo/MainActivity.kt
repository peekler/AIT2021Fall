package hu.ait.aittimedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import hu.ait.aittimedemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTime.setOnClickListener {
            var time = Date(System.currentTimeMillis()).toString()

            Toast.makeText(this, time, Toast.LENGTH_LONG).show()
            binding.tvData.text = time

            var num = binding.etNum.text.toString().toInt() + 5
            binding.tvData.text = "The number is: $num"
        }
    }
}