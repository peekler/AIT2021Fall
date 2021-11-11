package hu.bme.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAnim = findViewById<Button>(R.id.btnAnimation)
        val animation = AnimationUtils.loadAnimation(this,
            R.anim.demo_anim)

        animation.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                Toast.makeText(this@MainActivity, "Animation over", Toast.LENGTH_LONG).show()
                // start another Activity here...
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }
        })


        btnAnim.setOnClickListener {

            btnAnim.startAnimation(animation)
        }

    }
}