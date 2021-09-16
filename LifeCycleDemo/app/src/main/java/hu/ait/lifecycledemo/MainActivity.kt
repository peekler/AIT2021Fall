package hu.ait.lifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    var score = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Log.d("TAG_LIFE", "onCreate called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("KEY_SCORE", score)
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG_LIFE", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG_LIFE", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG_LIFE", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG_LIFE", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG_LIFE", "onDestroy called")
    }


}