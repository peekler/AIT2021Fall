package hu.bme.threadandtimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.threadandtimerdemo.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var enabled = false

    inner class MyThread : Thread() {
        override fun run() {
            while (enabled) {
                runOnUiThread {
                    binding.tvData.append("#")
                }

                sleep(1000)
            }
        }
    }

    inner class MyTimerTask : TimerTask() {
        override fun run() {
            runOnUiThread {
                binding.tvData.append("W")
            }
        }
    }

    lateinit var mainTimer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainTimer = Timer()

        binding.btnStart.setOnClickListener {
            if (!enabled) {
                enabled = true
                MyThread().start()
                binding.tvData.append("A")
            }

            mainTimer.schedule(MyTimerTask(), 3000, 1000)
        }

        binding.btnStop.setOnClickListener {
            enabled = false
            mainTimer.cancel()
        }
    }

    override fun onDestroy() {
        enabled = false
        try {
            mainTimer.cancel()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }
}