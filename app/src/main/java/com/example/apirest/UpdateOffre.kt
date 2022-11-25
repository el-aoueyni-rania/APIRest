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

class UpdateOffre : AppCompatActivity() {
    lateinit var code2 : EditText
    lateinit var intitule2 : EditText
    lateinit var specialite2 : EditText
    lateinit var societe2 : EditText
    lateinit var nbpostes2 : EditText
    lateinit var pays2 : EditText
    lateinit var btnUpdate : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_offre)

        code2 = findViewById(R.id.Ucode)
        intitule2 = findViewById(R.id.Uintitule)
        specialite2 = findViewById(R.id.Uspecialite)
        societe2 = findViewById(R.id.Usociete)
        nbpostes2 = findViewById(R.id.Unbposte)
        pays2 = findViewById(R.id.Upays)

        val extras = intent.extras
        val code1 = extras!!.getInt("code").toString()
        val cc = extras!!.getInt("code")
        val intitule1 = extras!!.getString("intitule")
        val specialite1 = extras!!.getString("specialite")
        val societe1 = extras!!.getString("societe")
        val nbpostes1 = extras!!.getInt("nbpostes").toString()
        val pays1 = extras!!.getString("pays")

        code2.setText(code1)
        intitule2.setText(intitule1)
        specialite2.setText(specialite1)
        societe2.setText(societe1)
        nbpostes2.setText(nbpostes1)
        pays2.setText(pays1)




        btnUpdate = findViewById(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            val code3 = findViewById<EditText>(R.id.Ucode).text.toString().toInt()
            val intitule3 = findViewById<EditText>(R.id.Uintitule).text.toString().trim()
            val specialite3 = findViewById<EditText>(R.id.Uspecialite).text.toString().trim()
            val societe3 = findViewById<EditText>(R.id.Usociete).text.toString().trim()
            val nbposte3 = findViewById<EditText>(R.id.Unbposte).text.toString().toInt()
            val pays3 = findViewById<EditText>(R.id.Upays).text.toString().trim()
            val updateOffre = Offre(code3, intitule3, specialite3, societe3, nbposte3, pays3)



            val scope = CoroutineScope(Dispatchers.Main)
            scope.launch {
                try {
                    APIClient.apiService.updateOffre(updateOffre ,cc )
                    Toast.makeText(this@UpdateOffre, "Updated successfully !", Toast.LENGTH_SHORT).show()
                    intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)

                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }


            }
        }

    }
}