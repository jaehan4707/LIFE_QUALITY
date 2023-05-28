package com.example.myapplication

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.SplashActivity.Companion.token
import com.example.myapplication.SplashActivity.Companion.user
import com.example.myapplication.databinding.ActivityAgreeBinding
import com.example.myapplication.databinding.AgreeDialogBinding
import com.example.myapplication.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.model.mutation.Precondition.exists

class AgreeActivity : AppCompatActivity() { //개인정보 동의하는 액티비티
    val binding: ActivityAgreeBinding by lazy {
        ActivityAgreeBinding.inflate(layoutInflater)
    }
    private var Sex : String = ""
    private var Age : String =""
    private  var Family : String =""
    private var Study : String =""
    private  var Helath : String =""
    private  var Smoke : String=""
    private  var Drink : String=""
    private var phone : String =""
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java) //intent
        val database = FirebaseDatabase.getInstance()
        val userRef =
            database.getReference("User/token/${token!!}") //toekn 경로에 저장한다.

        Log.d("problem", "FCM token is ${token}")
        //여기서 토큰이 없다면 바로 넘어가야함.
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("problem", "해당 token 값이 이미 존재합니다. 작업을 멈추고, MainActivity로 이동합니다.")
                    val userInfoRef = database.getReference("User/token/$token/infomation")
                    userInfoRef.addListenerForSingleValueEvent(object : ValueEventListener {
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
                                            "age"->Age=value
                                            "smoke"->Smoke=value
                                            "family_relation"->Family=value
                                            "Drink"->Drink=value
                                            "sex"->Sex=value
                                            "medical_insurance"->Helath=value
                                            "phone"->phone=value
                                            "scholarship"->Study=value
                                        }
                                    }
                                }
                            }
                            user=User(Sex,Age,Family,Study,Helath,Smoke,Drink,phone)
                            Log.d("problem", "user : ${user}")
                        }
                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.d("problem", "데이터베이스 읽기 작업이 취소되었습니다.", databaseError.toException())
                        }
                    })
                    startActivity(intent)
                    setContentView(binding.root)
                    return
                }
                setContentView(binding.root)
                // 해당 token 값이 존재하지 않으므로 작업을 계속 진행합니다.
                userRef.setValue(SplashActivity.token)
                    .addOnSuccessListener {
                        Log.d("problem", "token 저장 성공")
                        // 작업을 진행할 코드를 추가하세요
                    }
                    .addOnFailureListener {
                        Log.d("problem", "token 저장 실패")
                    }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // 읽기 작업이 취소된 경우에 호출됩니다.
                Log.d("problem", "데이터베이스 읽기 작업이 취소되었습니다.", databaseError.toException())
            }
        })
        binding.yes.setOnClickListener {
            val dialogView = LayoutInflater.from(this@AgreeActivity).inflate(R.layout.agree_dialog, null)
            val dialogBinding = AgreeDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(this)
            val dialogLayoutParams = WindowManager.LayoutParams().apply {
                copyFrom(dialog.window?.attributes)
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.MATCH_PARENT
            }
            dialog.window?.attributes = dialogLayoutParams
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(dialogBinding.root)
            dialog.setCancelable(false)
            val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels
            dialog.window?.setLayout(screenWidth, screenHeight)
            dialog.show()
            clickRadio(dialogBinding, dialogBinding.rgSex, "Sex")
            clickRadio(dialogBinding, dialogBinding.rgAge, "Age")
            clickRadio(dialogBinding, dialogBinding.rgFamily, "Family")
            clickRadio(dialogBinding, dialogBinding.rgStudy, "Scholarship")
            clickRadio(dialogBinding, dialogBinding.rgHealth, "Medical_insurance")
            clickRadio(dialogBinding, dialogBinding.rgDrink, "Drink")
            clickRadio(dialogBinding, dialogBinding.rgSmoke, "Smoke")

            var selectSex = dialogBinding.rgSex.checkedRadioButtonId
            var selectAge = dialogBinding.rgAge.checkedRadioButtonId
            var selectFamily = dialogBinding.rgFamily.checkedRadioButtonId
            var selectStudy = dialogBinding.rgStudy.checkedRadioButtonId
            var selectSmoke = dialogBinding.rgSmoke.checkedRadioButtonId
            var selectDrink = dialogBinding.rgDrink.checkedRadioButtonId
            var selectHealth = dialogBinding.rgHealth.checkedRadioButtonId

            closeDialog(dialogBinding.root)
            dialogBinding.agreeClear.setOnClickListener {
                val phoneNumber = dialogBinding.editPhone.text.toString()

                val isValidPhoneNumber = android.util.Patterns.PHONE.matcher(phoneNumber).matches()
                if (!isValidPhoneNumber || Sex.isEmpty() || Age.isEmpty() || Family.isEmpty()||Study.isEmpty()
                    ||Helath.isEmpty()||Smoke.isEmpty()||Drink.isEmpty()) { // 입력된게 전화번호 형식이면
                        Toast.makeText(this, "빈칸이 있어요!! 선택을 완벽하게 해주세요", Toast.LENGTH_SHORT).show()
                    } else {
                        user=User(Sex,Age,Family,Study,Helath,Smoke,Drink,phoneNumber)
                    val infoRef =
                        database.getReference("User/token/${token!!}/infomation/") //toekn 경로에 저장한다.
                    infoRef.setValue(user).addOnSuccessListener {
                        Log.d("problem", "answer 저장 성공")
                        }
                        .addOnFailureListener { exception ->
                            Log.d("problem", "answer 저장 실패", exception)
                        }

                        Log.d("problem", "${user}")
                        dialog.dismiss() // 다이얼로그를 닫기
                        startActivity(intent)
                    }
                }

            }
            binding.no.setOnClickListener {
                Toast.makeText(this, "참여를 원치 않으신다면 해당 어플리케이션을 사용하실 수 없습니다", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    fun closeDialog(rootView: View){
        rootView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // 터치 이벤트가 발생하면 키보드를 숨깁니다.
                Log.d("test","터치이벤트가 발생해서 키보드르 숨깁니다.")
                val inputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(rootView.windowToken, 0)
                rootView.clearFocus()
            }
            false
        }
    }
    fun clickRadio(binding: AgreeDialogBinding, radioGroup: RadioGroup, str: String) {
        radioGroup.setOnCheckedChangeListener { group, checkedId -> // 라디오 버튼의 선택 상태가 변경되었을 때 호출되는 메서드입니다.
            val radioButton = radioGroup.findViewById<RadioButton>(checkedId)
            val selectedText = radioButton.text.toString()
            when(str){
                "Sex"-> Sex=selectedText
                "Age"->Age=selectedText
                "Family"->Family=selectedText
                "Scholarship"->Study=selectedText
                "Medical_insurance" ->Helath=selectedText
                "Smoke"->Smoke=selectedText
                "Drink"->Drink=selectedText
            }
        }
    }
}