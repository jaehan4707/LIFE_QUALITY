package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityAdminHomeBinding
import com.example.myapplication.databinding.QuestionItemBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Math.log


class HomeViewHolder(val binding : QuestionItemBinding) : RecyclerView.ViewHolder(binding.root)
class AdminHomeAdapter(val dataset : MutableList<TotalSurvey>, val binding2 : ActivityAdminHomeBinding) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            QuestionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("test","home view holder에 들어왓습니다")
        val viewHolder = holder as HomeViewHolder
        holder.binding.listTitle.text = dataset[position].title
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
class ChoiceAdapter(val dataset : MutableList<TotalSurvey>, val binding2 : ActivityAdminHomeBinding) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            QuestionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val viewHolder = holder as HomeViewHolder
        holder.binding.listTitle.text = dataset[position].title

        if(binding2.choice.isSelected) { //편집버튼 활성화
            holder.binding.adminRecycler.setOnClickListener {
                setposition(position)
                Log.d("test", "${position}")
            }
            if (dataset[position].selected) { //true
                Log.d("test", "${position} 뷰는 비활성화 -> 활성화 시키겠습니다.")
                holder.binding.listTitle.setBackgroundColor(Color.parseColor("#EE913B"))

            } else { //false
                Log.d("test", "${position} 뷰는 활성화-> 비활성화시키겠습니다.")
                holder.binding.listTitle.setBackgroundColor(Color.parseColor("#60EE913B"))

            }
        }
        binding2.trash.setOnClickListener{
            var ary = arrayListOf<Int>()
            for(i in 0 .. dataset.size-1){
                if(dataset[i].selected) //선택된놈이라면
                {
                    ary.add(i)
                }
            }
            val Db = Firebase.firestore
            for(i in 0 .. ary.size-1)
            {
                val collectionRef = Db.collection(dataset[ary[i]].surveyType)
                val query : Query = collectionRef.whereEqualTo("title",dataset[ary[i]].title)
                val task : Task<QuerySnapshot> = query.get()
                task.addOnSuccessListener { querySnapshot->
                    val documents : MutableList<DocumentSnapshot> =querySnapshot.documents
                    for(document in documents){
                        Log.d("test","문서경로 : ${document.reference.id}")
                        collectionRef.document(document.reference.id).delete()
                    }
                }.addOnFailureListener{
                    Log.d("test","실패")
                }
            }
            for( i in 0 .. ary.size-1)
            {
                dataset.removeAt(ary[i]-i)
            }
            notifyDataSetChanged()
            binding2.plus.visibility= View.VISIBLE
            binding2.trash.visibility=View.GONE
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun setposition(position: Int) {
        dataset[position].selected = dataset[position].selected != true
        notifyDataSetChanged()
    }
}


