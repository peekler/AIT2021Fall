package hu.ait.aithello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.aithello.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShow.setOnClickListener {
            var dateTime = Date(System.currentTimeMillis())

            binding.tvData.text = dateTime.toString()
        }

    }

}