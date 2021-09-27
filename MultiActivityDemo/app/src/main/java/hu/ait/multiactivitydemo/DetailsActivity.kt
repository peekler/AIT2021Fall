package hu.ait.multiactivitydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.multiactivitydemo.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //getIntent() - this intent has started the Activity,
        //so I can extract the arguments from it
        val data = intent.getStringExtra(MainActivity.KEY_DATA)
        binding.tvData.text = data

        binding.tvData.text = DataManager.name

    }
}