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

class LoginActivity : AppCompatActivity() {
    lateinit var binding : LoginLayoutBinding
    //입력한 phone, pw를 db에 저장된 것과 비교하기.
    lateinit var phone : String
    lateinit var pw : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //로그인 버튼 클릭시
        binding.login.setOnClickListener() {
            var phone = binding.phoneNum.text.toString()
            Log.d("test","$phone")

            //get방식으로, 입력한 phoneNumber에 대한 user정보를 가져오는 API 호출
            apiLogin()

            //이제 가져온 user정보에 있는 passward와 pw(내가 입력한 pw)를 비교해야함!
            //비교해서 일치하면, 로그인 성공
            //그렇지 않으면, 다시 로그인해야함!

            if(phone.length != 11) { //핸드폰번호가 올바르게 입력되지 않았다면?
                Snackbar.make(binding.loginLayout, "핸드폰 번호 또는 비밀번호가 올바르지 않습니다.", Snackbar.LENGTH_SHORT).show()
            }
            else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        //회원가입 버튼 클릭시
        binding.goRegister.setOnClickListener() {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun apiLogin() {

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
                Log.d("ApiRequest", "${response.body()}")
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