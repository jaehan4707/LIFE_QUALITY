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
import android.view.ViewTreeObserver
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.weight
import com.example.myapplication.databinding.FragmentEq5dBinding
import com.example.myapplication.databinding.FragmentFallBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FallFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class  FallFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentFallBinding? = null
    private val binding: FragmentFallBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFallBinding.inflate(inflater, container, false)
        val startIndex = binding.info.text.indexOf("높을수록")
        val endIndex = binding.info.text.indexOf("높음") + "높음".length
        val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red)) // 색깔 지정
        val spannableString = SpannableString(binding.info.text)
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.info.text = spannableString

        when(weight.toInt()) {
            in 14 .. 28 -> {
                binding.redLight.setBackgroundResource(R.drawable.red_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.fallResult.setText(R.string.red_eq5d)
                val text="높은"
                val startIndex = text.indexOf("높은")
                val endIndex = startIndex + "높은".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red_circle)) // 색깔 지정
                val spannableString = SpannableString(binding.fallResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.fallResult.text = spannableString
            }

            in 9 .. 13-> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.yellow_circle)
                binding.greenLight.setBackgroundResource(R.drawable.gray_circle)
                binding.fallResult.setText(R.string.yellow_eq5d)
                val text="중간"
                val startIndex = text.indexOf("중간")
                val endIndex = startIndex + "중간".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.main_orange)) // 색깔 지정
                val spannableString = SpannableString(binding.fallResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.fallResult.text = spannableString
            }
            in 7 .. 8 -> {
                binding.redLight.setBackgroundResource(R.drawable.gray_circle)
                binding.yellowLight.setBackgroundResource(R.drawable.gray_circle)
                binding.greenLight.setBackgroundResource(R.drawable.green_circle)
                binding.fallResult.setText(R.string.green_eq5d)
                val text="낮은"
                val startIndex = text.indexOf("낮은")
                val endIndex = startIndex + "낮은".length
                val colorSpan = ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.green_circle)) // 색깔 지정
                val spannableString = SpannableString(binding.fallResult.text)
                spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.fallResult.text = spannableString
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