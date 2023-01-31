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
import android.widget.Filter
import android.widget.Filterable
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
class EditAdapter(val dataset : MutableList<TotalSurvey>, val binding2 : ActivityAdminHomeBinding) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),Filterable
{

    var filterSurvey = dataset
    var itemFilter = ItemFilter()
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

        Log.d("test","에디터어뎁터")
        val viewHolder = holder as HomeViewHolder
        holder.binding.listTitle.text = filterSurvey[position].title
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun getFilter(): Filter {
        return itemFilter
    }
    inner class ItemFilter : Filter(){
        override fun performFiltering(p0: CharSequence?): FilterResults {
            Log.d("test"," 1 : ${filterSurvey.size}")
            Log.d("test","2 : ${dataset.size}")
            val filterString = p0.toString()
            val result = FilterResults()
           if(filterString.trim { it <= ' ' }.isNotEmpty()){ //한글자 이상이라도 들어오면
              // filterSurvey.clear()
               Log.d("test","입력창에 한글자라도 들어왔습니다, ${filterString}")
               for(i in 0 .. dataset.size-1){

                   if(dataset[i].title.contains(filterString)) { //포함되어 있다면
                       filterSurvey.add(dataset[i])
                       Log.d("test","${dataset[i].title}")
                   }
               }
           }
            result.values=filterSurvey
            result.count= filterSurvey.size
            Log.d("test","${result.count}")
            return result
        }

        override fun publishResults(p0: CharSequence, p1: FilterResults) {
            filterSurvey.clear() // 초기화
            filterSurvey.addAll(p1.values as MutableList<TotalSurvey>)
            Log.d("test","${filterSurvey.size}")
            notifyDataSetChanged()
        }
    }
}
