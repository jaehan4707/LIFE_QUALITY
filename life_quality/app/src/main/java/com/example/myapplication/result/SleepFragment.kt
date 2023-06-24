package com.example.myapplication.result

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.ResultLayout
import com.example.myapplication.ResultLayout.Companion.weight
import com.example.myapplication.SplashActivity
import com.example.myapplication.databinding.FragmentEq5dBinding
import com.example.myapplication.databinding.FragmentSleepBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SleepFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SleepFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentSleepBinding? = null
    private val binding: FragmentSleepBinding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSleepBinding.inflate(inflater, container, false)

        //점수를 계산하자.
        if(weight>= 8.5) {//good
            binding.sleepResult.setText(R.string.good_sleep)
            SplashActivity._result.sleep = "잘 주무시고 계십니다"
            binding.sleepResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.green_circle))
        }
        else{
            binding.sleepResult.setText(R.string.bad_sleep)
            SplashActivity._result.sleep = "잘 못 주무시고 계십니다"
            binding.sleepResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
        }
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}