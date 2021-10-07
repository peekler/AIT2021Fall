package hu.bme.viewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import hu.bme.viewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivityMainBinding
    var cityNames = arrayOf("New York", "New Delhi", "New Orleans", "Budapest", "Bukarest")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            cityNames
        )
        binding.autoCompleteCities.setAdapter(cityAdapter)
        binding.autoCompleteCities.threshold = 1

        binding.rbtnGreen.setOnClickListener {
            binding.root.setBackgroundColor(Color.GREEN)
        }
        binding.rbtnRed.setOnClickListener {
            binding.root.setBackgroundColor(Color.RED)
        }
        binding.rbtnWhite.setOnClickListener {
            binding.root.setBackgroundColor(Color.WHITE)
        }


        val fruitsAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.fruits_array,
            android.R.layout.simple_spinner_item
        )
        fruitsAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        binding.spinnerFruits.adapter = fruitsAdapter

        binding.spinnerFruits.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this,
            binding.spinnerFruits.getItemAtPosition(position).toString(),
            Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}