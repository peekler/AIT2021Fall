package hu.bme.fragmentbasicdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentMain : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(
            R.layout.fragment_main, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()

        val btnDemo = view?.findViewById<Button>(R.id.btnDemo)
        btnDemo?.setOnClickListener {
            Toast.makeText(activity, "Demo works", Toast.LENGTH_LONG).show()
        }

    }

}