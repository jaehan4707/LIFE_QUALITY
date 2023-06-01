package com.example.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.snapshots.Snapshot
import com.example.myapplication.AgreeActivity.Companion.phone
import com.example.myapplication.model.User
import com.example.myapplication.databinding.ActivitySplashBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.*
import java.lang.reflect.Array.set

class SplashActivity : AppCompatActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)
    val TAG = "SplashActivity"
    companion object {
        lateinit var user: User
        lateinit var databaseReference: DatabaseReference
        //lateinit var authReference: FirebaseAuth
        lateinit var fcmReference: FirebaseMessaging
        var token : String? = null
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = FirebaseDatabase.getInstance()
        fcmReference = FirebaseMessaging.getInstance()
        user=User("","","","","","","")

        /*
        getToken { token ->
            if (token!= null) {
                val Db = Firebase.firestore
                val result = hashMapOf(
                    "title" to token.toString()
                )
                Log.d("problem", "${result}")
                Db.collection("User").document("token")
                    .set(result)
                    .addOnSuccessListener { Log.d("problem", "저장성공") }
                    .addOnFailureListener { Log.d("problem", "저장실패") }
                activityScope.launch {
                    delay(1000)
                    //val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    val intent = Intent(this@SplashActivity, AgreeActivity::class.java)
                    //val intent = Intent(this@SplashActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            else {

            }
        }
         */
        /*
        getToken { token ->
            if (token != null) {
                val Db = Firebase.firestore
                val tokenRef = Db.collection("User").document("token")

                tokenRef
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) { //존재할경우
                            val currentTokens = document.get("title") as? ArrayList<String>
                            if (currentTokens != null) {
                                if (currentTokens.contains(token)) {
                                    // 토큰 값이 이미 존재하는 경우 처리
                                    Log.d("problem", "토큰이 이미 존재합니다 -> main으로 넘어갑니다..")
                                    val intent = Intent(this@SplashActivity,MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    currentTokens.add(token)
                                    tokenRef.update("title", currentTokens)
                                    Log.d("problem", "토큰 추가 완료")
                                    val intent = Intent(this@SplashActivity, AgreeActivity::class.java)
                                    startActivity(intent)
                                }
                            } else {
                                // 기존에 title 필드가 없는 경우 새로운 리스트 생성
                                val newTokens = arrayListOf(token)
                                tokenRef.update("title", newTokens)
                                Log.d("problem", "토큰 추가 완료")
                                val intent = Intent(this@SplashActivity, AgreeActivity::class.java)
                                startActivity(intent)
                            }
                        } else {
                            // 문서가 존재하지 않는 경우 새로운 문서 생성
                            val newTokens = arrayListOf(token)
                            tokenRef.set(hashMapOf("title" to newTokens))
                            Log.d("problem", "토큰 추가 완료")
                            val intent = Intent(this@SplashActivity, AgreeActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.d("problem", "저장실패: $e")
                    }
            } else {
                // Token이 null인 경우 처리
            }
        }
    }
        */
        getToken { token ->
            if (token != null) {
                val Db = Firebase.firestore
                val tokenRef = Db.collection("User").document(token)
                tokenRef
                    .get()
                    .addOnSuccessListener { document ->
                        if (document.exists()) { // 존재할 경우
                            // 이미 콜렉션이 존재하는 경우 처리
                            //콜랙션에 폰번호전보흫 받아야함.
                            Log.d("problem", "콜렉션이 이미 존재합니다 -> main으로 넘어갑니다..")
                            //main으로 넘어가기전에 firebase에 유저정보를 쫙 받자.
                            val tokenRef = Db.collection("User").document(token)
                            tokenRef.get()
                                .addOnSuccessListener { document ->
                                    if (document.exists()) {
                                        phone = document.getString("phone")!!
                                        if (phone != null) { // phone 필드에 값이 있을 경우
                                            Log.d("problem", "phone 필드 값: $phone")
                                            val phoneRef = database.getReference("User/phone/${phone}/information") //toekn 경로에 저장한다.
                                                phoneRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        Log.d("problem", "해당 info 값이 이미 존재합니다. 작업을 멈추고, MainActivity로 이동합니다.")
                                                        val infoType = object : GenericTypeIndicator<Map<String, String>>() {}
                                                        val info = dataSnapshot.getValue(infoType)
                                                        if (info != null) {
                                                            // info에 저장된 정보를 사용합니다.
                                                            for ((key, value) in info) {
                                                                Log.d("problem", "Key: $key, Value: $value")
                                                                when(key){
                                                                    "age"->user.Age=value
                                                                    "smoke"->user.Smoke=value
                                                                    "family_relation"->user.Family_relation=value
                                                                    "drink"->user.Drink=value
                                                                    "sex"->user.Sex=value
                                                                    "medical_insurance"->user.Medical_insurance=value
                                                                    "scholarship"->user.Scholarship=value
                                                                }
                                                            }
                                                        }
                                                    }
                                                    else {
                                                        Log.d("problem", "phone 필드가 없습니다.")
                                                    }
                                                    Log.d("problem", "user : ${user}")
                                                }
                                                override fun onCancelled(databaseError: DatabaseError) {
                                                    Log.d("problem", "데이터베이스 읽기 작업이 취소되었습니다.", databaseError.toException())
                                                }
                                            })
                                        }
                                        // phone 필드 값을 사용하여 원하는 작업을 수행할 수 있습니다.
                                    }
                                    else
                                    {
                                        Log.d("problem", "문서가 존재하지 않습니다.")
                                    }
                                }
                                .addOnFailureListener { e ->
                                    Log.d("problem", "phone 필드 가져오기 실패: $e")
                                }
                                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                // 새로운 콜렉션 생성
                                tokenRef.set(hashMapOf("phone" to ""))
                                    .addOnSuccessListener {
                                        Log.d("problem", "콜렉션 추가 완료")
                                        val intent =
                                            Intent(this@SplashActivity, AgreeActivity::class.java)
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener { e ->
                                        Log.d("problem", "콜렉션 추가 실패: $e")
                                    }
                            }
                        }
                    .addOnFailureListener { e ->
                        Log.d("problem", "저장 실패: $e")
                    }
            } else {
                // Token이 null인 경우 처리
            }
        }
    }



        private fun getToken(completion: (String?) -> Unit){

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("problem", "FCM 토큰 등록 실패", task.exception)
                return@OnCompleteListener
            }
            token = task.result
            completion(token)
            Log.d("problem","token : ${token.toString()}")
        })

    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}