package hu.bme.aut.fragmentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class FragmentMain : Fragment() {

    companion object {
        const val TAG = "FragmentMain"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

        rootView.btnDemo.setOnClickListener {
            Toast.makeText(activity, "Demo text", Toast.LENGTH_SHORT).show()

            (activity as MainActivity).doSomething()
        }

        return rootView
    }

    fun doFragmentStuff() {

    }
}