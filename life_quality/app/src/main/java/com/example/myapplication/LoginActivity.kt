package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.LoginLayoutBinding
import com.example.retrofitpractice.Model.Data
import com.example.retrofitpractice.service.ApiService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding : LoginLayoutBinding
    //입력한 phone, pw를 db에 저장된 것과 비교하기.
    lateinit var respPhone : String
    lateinit var respPassword : String
    lateinit var pw : String
    lateinit var phone : String
    var loginFlag = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //로그인 버튼 클릭시
        binding.login.setOnClickListener() {
<<<<<<< HEAD
            pw = binding.inputPassword.text.toString()
            phone = binding.inputPhone.text.toString()
            Log.d("LoginTest", "inputPhone : ${phone}, inputPassword : ${pw}")
=======
            phone = binding.phoneNum.text.toString()
            Log.d("test","$phone")
>>>>>>> 0fabbf121017e6d131e776b02fc11fbed4166efb

            //get방식으로, 입력한 phoneNumber에 대한 user정보를 가져오는 API 호출
            runBlocking {
                val job = CoroutineScope(Dispatchers.IO).launch{
                    apiLogin()
                }
                job.join()
            }
        }

        //회원가입 버튼 클릭시
        binding.goRegister.setOnClickListener() {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun apiLogin() {

        //1. retrofit 객체 생성
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //2. service 객체 생성

        val apiservice : ApiService = retrofit.create(ApiService::class.java)

        //3. Call 객체 생성
        var registerCall = apiservice.selectPhoneUserProfile(phone)

        //4. 네트워크 통신
        registerCall.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                Log.d("Logintest", "Response : ${response.body()}")
                //가져온 정보에서 phoneNumber랑 password저장
                respPhone = response.body()?.phone.toString()
                respPassword = response.body()?.password.toString()
                //만약 db에 저장된 패스워드와 입력한 패스워드가 다르거나
                //phoneNumber와 입력한 phone 번호가 다르다면 로그인 하지 않기 위해 loginFlag를 -1로 변경한다.
                if(pw != respPassword || phone != respPhone) {
                    Log.d("LoginTest", "this is loginFlag")
                    loginFlag = -1
                }
                else {
                    loginFlag = 1
                }

                //로그인 검사
                if(phone.length != 11 || loginFlag == -1) { //핸드폰번호가 올바르게 입력되지 않았다면?
                    Snackbar.make(binding.loginLayout, "핸드폰 번호 또는 비밀번호가 올바르지 않습니다.", Snackbar.LENGTH_SHORT).show()
                    binding.inputPassword.setText("")
                }
                else {
                    Snackbar.make(binding.loginLayout, "로그인 성공!", Snackbar.LENGTH_SHORT).show()
                    runBlocking {
                        launch {
                            delay(500)
                        }
                    }
                    var intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                Log.d("LoginTest", "dpPhone : ${respPhone}, dbPassword : ${respPassword}")

//                binding.resultText.append("id : ${tickerInfo?.data?.id}\n")
//                binding.resultText.append("name : ${tickerInfo?.data?.name}\n")
//                binding.resultText.append("phone : ${tickerInfo?.data?.phone}\n")
//                binding.resultText.append("address : ${tickerInfo?.data?.address}\n")
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                //호출에 실패했을 때
                Log.d("ApiRequest", "호출 실패 씨발!!")
                call.cancel()
            }

        })


    }
}