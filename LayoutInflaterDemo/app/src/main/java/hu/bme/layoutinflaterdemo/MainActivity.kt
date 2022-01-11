package hu.bme.layoutinflaterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import hu.bme.layoutinflaterdemo.databinding.ActivityMainBinding
import hu.bme.layoutinflaterdemo.databinding.LayoutDetailsBinding

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
        //val detailsLayout = layoutInflater.inflate(R.layout.layout_details, null)
        val detailsLayoutBinding = LayoutDetailsBinding.inflate(layoutInflater)

        detailsLayoutBinding.btnDelete.setOnClickListener {
            binding.layoutContent.removeView(detailsLayoutBinding.root)
        }

        binding.layoutContent.addView(detailsLayoutBinding.root)
    }

}