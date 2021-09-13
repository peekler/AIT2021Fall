package hu.ait.aittimedemo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import hu.ait.aittimedemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTime.setOnClickListener {
            Log.d("TAG_DEMO", "btnTime was pressed")

            var time = Date(System.currentTimeMillis()).toString()

            //Toast.makeText(this, time, Toast.LENGTH_LONG).show()
            binding.tvData.text = time

            revealCard()

            Snackbar.make(binding.root, time, Snackbar.LENGTH_LONG).show()

            try {
                if (binding.etNum.text.isNotEmpty()) {
                    var num = binding.etNum.text.toString().toInt()
                    binding.tvData.text = "The result is ${num+14}"
                }
            }  catch (e: Exception) {
                e.printStackTrace()
                //binding.tvData.text = "The number is too large ${e.localizedMessage}"
                binding.etNum.error = getString(R.string.error_large_number)
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun revealCard() {
        val x = binding.cardView.getRight()
        val y = binding.cardView.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(binding.cardView.getWidth().toDouble(),
            binding.cardView.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            binding.cardView,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        binding.cardView.setVisibility(View.VISIBLE)
        anim.start()
    }
}