package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
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

    @SuppressLint("UseCompatLoadingForDrawables")
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
                //holder.binding.listTitle.setBackgroundColor(Color.parseColor("#EE913B"))

                holder.binding.listTitle.setBackgroundResource(R.drawable.selected_textview_round)
            } else { //false
                Log.d("test", "${position} 뷰는 활성화-> 비활성화시키겠습니다.")
                holder.binding.listTitle.setBackgroundResource(R.drawable.textview_round)
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
    var filterSurvey = mutableListOf<TotalSurvey>()
    var tempList =  mutableListOf<TotalSurvey>()
    var itemFilter = ItemFilter()
    init{
        filterSurvey.addAll(dataset)
    }
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
        Log.d("test","위치 : ${position}")
        if(position<filterSurvey.size) {
            if (filterSurvey.size != 0) {
                val viewHolder = holder as HomeViewHolder
                holder.binding.listTitle.text = filterSurvey[position].title
            }
        }

    }

    override fun getItemCount(): Int {
        //return dataset.size
        return filterSurvey.size
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
           if(filterString.trim { it <= ' '}.isNotEmpty()){ //한글자 이상이라도 들어오면
               Log.d("test","입력창에 한글자라도 들어왔습니다, ${filterString}")
               for(k in 0 until dataset.size){
                   if(dataset[k].title.contains(filterString)){
                       tempList.add(dataset[k])
                       Log.d("test","${dataset[k].title}")
                   }
               }
           }
            result.values=tempList //필러팅된 결과를 value에 넣고
            result.count= tempList.size //필터링된 결과의 수를 count에 넣음.
            Log.d("test","${result.count}") //결과의 수 print
            return result // -> 결과를 밑에 함수에 전송
        }
        override fun publishResults(p0: CharSequence, p1: FilterResults) {
            filterSurvey.clear() // 초기화
            filterSurvey.addAll(tempList)
            tempList.clear()
            //여기서 add가 제대로 이루어지지않음.
            Log.d("test","${filterSurvey.size}이고 끝마칩니다.")
            notifyDataSetChanged()
        }
    }
}
