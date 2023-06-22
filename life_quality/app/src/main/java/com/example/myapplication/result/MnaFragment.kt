package com.example.myapplication.result

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.traffic
import com.example.myapplication.databinding.FragmentEq5dBinding
import com.example.myapplication.databinding.FragmentMnaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MnaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MnaFragment: Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentMnaBinding? = null
    private val binding: FragmentMnaBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMnaBinding.inflate(inflater, container, false)

        when(traffic) {
            1 -> {
                binding.redLight.setBackgroundResource(R.drawable.red_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.mnaResult.setText(R.string.red_mna) //영양불량을 빨간색으로!
                val text="영양불량"
                val startIndex = text.indexOf("영양불량")
                val endIndex = startIndex + "영양불량".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red_circle)) // 색깔 지정
                val spannableString = SpannableString(binding.mnaResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.mnaResult.text = spannableString
            }
            2 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.mnaResult.setText(R.string.yellow_mna)
                val text="영양불량"
                val startIndex = text.indexOf("영양불량")
                val endIndex = startIndex + "영양불량".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.main_orange)) // 색깔 지정
                val spannableString = SpannableString(binding.mnaResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.mnaResult.text = spannableString
            }
            3 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                binding.mnaResult.setText(R.string.green_mna)
                val text="영양 관리"
                val startIndex = text.indexOf("영양 관리")
                val endIndex = startIndex + "영양 관리".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.green_circle)) // 색깔 지정
                val spannableString = SpannableString(binding.mnaResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.mnaResult.text = spannableString
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