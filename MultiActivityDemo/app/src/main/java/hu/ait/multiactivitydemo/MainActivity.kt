package hu.ait.multiactivitydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.multiactivitydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_DATA = "KEY_DATA"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            val intentDetails = Intent()
            //intentDetails.setClassName(this,
            //    "hu.ait.multiactivitydemo.DetailsActivity")

            intentDetails.setClass(this,
                DetailsActivity::class.java)

            intentDetails.putExtra(KEY_DATA,
                binding.etData.text.toString())

            DataManager.name = "Main"


            startActivity(intentDetails)

            finish() // removes the Acitivity from the back stack
        }

    }
}