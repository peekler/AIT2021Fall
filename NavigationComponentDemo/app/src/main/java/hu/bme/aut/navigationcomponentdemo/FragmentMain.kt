package hu.bme.aut.navigationcomponentdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_main.view.*


class FragmentMain : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btnDetails.setOnClickListener {
            view.findNavController().navigate(
                FragmentMainDirections.actionFragmentMainToFragmentDetails(Person("John Doe", "New York"))
            )
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = FragmentMain()
    }
}