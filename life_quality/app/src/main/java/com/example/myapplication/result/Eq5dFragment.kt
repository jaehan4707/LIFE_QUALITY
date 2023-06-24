package com.example.myapplication.result

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.traffic
import com.example.myapplication.SplashActivity.Companion._result
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
    @SuppressLint("ResourceAsColor")
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
                val text="낮은"
                _result.eq5d="낮은 상태입니다"
                val startIndex = text.indexOf("낮은")
                val endIndex = startIndex + "낮은".length
                //val colorSpan = BackgroundColorSpan(ContextCompat.getColor(requireContext(),R.color.pink)) // 색깔 지정
                val spannableString = SpannableString(binding.eq5dResult.text)
                //spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                val cSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red))
                spannableString.setSpan(cSpan,startIndex,endIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.eq5dResult.text = spannableString
            }

            2 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.eq5dResult.setText(R.string.yellow_eq5d)
                _result.eq5d="보통 상태입니다"
                val text="보통"
                val startIndex = text.indexOf("보통")
                val endIndex = startIndex + "보통".length
                val spannableString = SpannableString(binding.eq5dResult.text)
                val cSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.main_orange))
                spannableString.setSpan(cSpan,startIndex,endIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.eq5dResult.text = spannableString
            }
            3 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                binding.eq5dResult.setText(R.string.green_eq5d)
                _result.eq5d="높은 상태입니다"
                val text="높은"
                val startIndex = text.indexOf("높은")
                val endIndex = startIndex + "높은".length
                val spannableString = SpannableString(binding.eq5dResult.text)
                val cSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.green_circle))
                spannableString.setSpan(cSpan,startIndex,endIndex,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.eq5dResult.text = spannableString
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