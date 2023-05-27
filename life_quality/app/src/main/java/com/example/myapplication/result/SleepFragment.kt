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
            binding.sleepResult.text="잘 주무시고 계시네요!"
        }
        else{
            binding.sleepResult.text="아래의 수면건강 5계명을 읽어보세요!"
        }
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}