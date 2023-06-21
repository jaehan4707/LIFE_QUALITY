package com.example.myapplication.result

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

        Log.d("problem","노쇠측정 : ${weight}")
        when(weight.toInt()) {
            0 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                binding.frailtyResult.setText(R.string.green_frailty)
                val text="건강"
                val startIndex = text.indexOf("건강")
                val endIndex = startIndex + "건강".length
                val colorSpan = ForegroundColorSpan(Color.GREEN) // 색깔 지정
                val spannableString = SpannableString(binding.frailtyResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.frailtyResult.text = spannableString
            }

           in 1..2-> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
               binding.frailtyResult.setText(R.string.yellow_frailty)
               val text="노쇠 전"
               val startIndex = text.indexOf("노쇠 전")
               val endIndex = startIndex + "노쇠 전".length
               val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.main_orange)) // 색깔 지정
               val spannableString = SpannableString(binding.frailtyResult.text)
               spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
               binding.frailtyResult.text = spannableString
            }
            else-> {
                binding.redLight.setBackgroundResource(R.drawable.red_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.frailtyResult.setText(R.string.red_frailty)
                val text="노쇠"
                val startIndex = text.indexOf("노쇠")
                val endIndex = startIndex + "노쇠".length
                val colorSpan = ForegroundColorSpan(Color.RED) // 색깔 지정
                val spannableString = SpannableString(binding.frailtyResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.frailtyResult.text = spannableString
            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}