package com.example.apirest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOffre : AppCompatActivity() {
    lateinit var btnAdd : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_offre)


        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val code = findViewById<EditText>(R.id.Acode).text.toString().toInt()
            val intitule = findViewById<EditText>(R.id.Aintitule).text.toString().trim()
            val specialite = findViewById<EditText>(R.id.Aspecialite).text.toString().trim()
            val societe = findViewById<EditText>(R.id.Asociete).text.toString().trim()
            val nbposte = findViewById<EditText>(R.id.Anbposte).text.toString().toInt()
            val pays = findViewById<EditText>(R.id.Apays).text.toString().trim()
            val registerOffre = Offre(code, intitule, specialite, societe, nbposte, pays)



            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                try {
                    APIClient.apiService.addOffre(registerOffre)
                        .enqueue(object : Callback<ResponseBody> {

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Toast.makeText(this@AddOffre, t.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                                Toast.makeText(this@AddOffre, "Added successfully!", Toast.LENGTH_SHORT).show()
                                intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)

                        }
                    })
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }


            }
        }
    }
}