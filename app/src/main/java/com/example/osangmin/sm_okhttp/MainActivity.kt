package com.example.osangmin.sm_okhttp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import okhttp3.*

import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // GSON PART fucking json TYPe
        var json_test = "{ \"name\":\"John\", \"age\": \"31\", \"city\": \"New York\" }"
        var gson_test = Gson();
        var userDTO = gson_test.fromJson(json_test,UserDTO::class.java);

        println(userDTO.name)
        println(userDTO.age.toString())
        println(userDTO.city);

        // PaPago Part

        val JSON = MediaType.parse("application/json; charset=utf-8")

        val client = OkHttpClient()

        var url = "https://openapi.naver.com/v1/papago/n2mt";
        var json = JSONObject();
        json.put("source", "ko");
        json.put("target", "en");
        json.put("text", "만약 당신만 던지지 않는다면 이길 수 있어요 ");

        val body = RequestBody.create(JSON, json.toString())
        val request = Request.Builder()
                .header("X-Naver-Client-Id","Z6UfLokKPFJmKbbM3XLM")
                .addHeader("X-Naver-Client-Secret", "KOFa6xt1j2")
                .url(url)
                .post(body)
                .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                println("????????????????/");
            }

            override fun onResponse(call: Call?, response: Response?) {

                //성공 했을 때 발생하는 함수
                var str = response!!.body()!!.string();
                println(str);

                var papagoDTO = Gson().fromJson<PapagoDTO>(str,PapagoDTO::class.java)
                println(papagoDTO.message!!.result!!.translatedText)
            }
        })




    }
}
