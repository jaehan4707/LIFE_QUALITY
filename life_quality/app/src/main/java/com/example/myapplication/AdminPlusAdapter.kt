package com.example.myapplication

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityAdminPlusBinding
import com.example.myapplication.databinding.AdminPlusItemBinding


class PlusViewHolder(val binding : AdminPlusItemBinding) : RecyclerView.ViewHolder(binding.root)
class AdminPlusAdapter(val dataset : MutableList<Answer>, val binding2 : ActivityAdminPlusBinding) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlusViewHolder(
            AdminPlusItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as PlusViewHolder).binding
        binding.plusContent.addTextChangedListener(TextWatcher(position))
        binding.inputScore.addTextChangedListener(scoreWatcher(position))

    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    inner class TextWatcher(var position : Int) : android.text.TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            dataset[position].content=p0.toString()
        }
    }
    inner class scoreWatcher(var position : Int) : android.text.TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            dataset[position].score=p0.toString()
        }
    }
}


