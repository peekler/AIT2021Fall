package hu.bme.aut.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //(supportFragmentManager.findFragmentByTag("FragmentMain") as FragmentMain).doFragmentStuff()

        btnMain.setOnClickListener {
            showFragmentByTag(FragmentMain.TAG)
        }

        btnDetails.setOnClickListener {
            showFragmentByTag(FragmentDetails.TAG)
        }
    }

    fun doSomething() {
        //..
    }

    // Dynamic fragment attachment
    fun showFragmentByTag(tag: String) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)

        if (fragment == null) {
            if (tag == FragmentMain.TAG) {
                fragment = FragmentMain()
            } else {
                fragment = FragmentDetails()
            }
        }

        var fragTrans = supportFragmentManager.beginTransaction()
        fragTrans.replace(R.id.fragmentContainer, fragment, tag)

        //fragTrans.addToBackStack("")
        fragTrans.commit()
    }


}