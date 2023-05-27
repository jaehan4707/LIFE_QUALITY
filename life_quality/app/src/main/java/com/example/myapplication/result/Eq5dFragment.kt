package com.example.myapplication.result

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.traffic
import com.example.myapplication.databinding.FragmentEq5dBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Eq5dFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Eq5dFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentEq5dBinding? = null
    private val binding: FragmentEq5dBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEq5dBinding.inflate(inflater, container, false)

        when(traffic) {
            1 -> {
                binding.redLight.setBackgroundResource(R.drawable.red_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.eq5dResult.setText(R.string.red_eq5d)
                binding.eq5dResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }

            2 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.eq5dResult.setText(R.string.yellow_eq5d)
                binding.eq5dResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.yellow))
            }

            3 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                binding.eq5dResult.setText(R.string.green_eq5d)
                binding.eq5dResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }

            else -> false
        }
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}