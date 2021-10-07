package hu.bme.layoutinflaterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hu.bme.layoutinflaterdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            addDetailsElement()
        }
    }

    private fun addDetailsElement() {
        val detailsLayout = layoutInflater.inflate(R.layout.layout_details, null)

        detailsLayout.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            binding.layoutContent.removeView(detailsLayout)
        }

        binding.layoutContent.addView(detailsLayout)
    }

}