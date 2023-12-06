package and09.multiweatherapp.ui.home

import and09.multiweatherapp.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import and09.multiweatherapp.databinding.FragmentHomeBinding
import android.widget.ImageView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textViewLocation = binding.textviewLocation
        homeViewModel.location.observe(viewLifecycleOwner) {
            textViewLocation.text = it
        }
        val textViewTemperature = binding.textviewTemperature
        homeViewModel.temperature.observe(viewLifecycleOwner) {
            textViewTemperature.text = it
        }
        //different approach
        val textViewDescription =
            root.findViewById<TextView>(R.id.textview_description)
        homeViewModel.description.observe(viewLifecycleOwner) {
            textViewDescription.text = it
        }
        val textViewProvider =
            root.findViewById<TextView>(R.id.textview_provider)
        homeViewModel.provider.observe(viewLifecycleOwner) {
            textViewProvider.text = it
        }
        val imageView =
            root.findViewById<ImageView>(R.id.imageview_weathericon)
        homeViewModel.iconBitmap.observe(viewLifecycleOwner) {
            imageView.setImageBitmap(it)
        }

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        homeViewModel.doAction()*/
        homeViewModel.retrieveWeatherData()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}