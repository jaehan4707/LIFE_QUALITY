package com.example.myapplication.result

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.weight
import com.example.myapplication.databinding.FragmentFallBinding
import com.example.myapplication.databinding.FragmentFrailtyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FrailtyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class  FrailtyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentFrailtyBinding? = null
    private val binding: FragmentFrailtyBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFrailtyBinding.inflate(inflater, container, false)

        when(weight.toInt()) {
            0 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
            }

           in 1..2-> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
            }
            else-> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}