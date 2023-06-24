package com.example.myapplication.result

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.traffic
import com.example.myapplication.SplashActivity
import com.example.myapplication.databinding.FragmentEq5dBinding
import com.example.myapplication.databinding.FragmentSgdskBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SgdskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SgdskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentSgdskBinding? = null
    private val binding: FragmentSgdskBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSgdskBinding.inflate(inflater, container, false)

        when(ResultLayout.weight.toInt()){
            in 0..5 -> traffic=3
            in 6..9 -> traffic=2
            in 10 .. 15-> traffic=1
        }
        when(traffic) {
            1 -> {
                binding.redLight.setBackgroundResource(R.drawable.red_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.sgdskResult.setText(R.string.red_sgdsk)
                SplashActivity._result.sgdsk = "심한우울 상태입니다"
                val text="심한우울"
                val startIndex = text.indexOf("심한우울")
                val endIndex = startIndex + "심한우울".length
                val colorSpan = ForegroundColorSpan(Color.RED) // 색상 지정
                val spannableString = SpannableString(binding.sgdskResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.sgdskResult.text = spannableString
            }
            2 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.sgdskResult.setText(R.string.yellow_sgdsk)
                SplashActivity._result.sgdsk = "가벼운 우울 상태입니다"
                val text="가벼운 우울"
                val startIndex = text.indexOf("가벼운 우울")
                val endIndex = startIndex + "가벼운 우울".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.main_orange)) // 색깔 지정
                val spannableString = SpannableString(binding.sgdskResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.sgdskResult.text = spannableString
            }

            3 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                binding.sgdskResult.setText(R.string.green_sgdsk)
                SplashActivity._result.sgdsk = "정상입니다"
                val text="정상"
                val startIndex = text.indexOf(text)
                val endIndex = startIndex + "정상".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.green_circle)) // 색깔 지정
                val spannableString = SpannableString(binding.sgdskResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.sgdskResult.text = spannableString
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