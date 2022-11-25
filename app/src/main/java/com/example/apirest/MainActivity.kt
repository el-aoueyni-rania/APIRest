package com.example.apirest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() ,MyAdapter.OnItemClickListener {
lateinit var btnAddF : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        funGet()

        btnAddF = findViewById(R.id.fab)
        btnAddF.setOnClickListener{
            intent = Intent(applicationContext, AddOffre::class.java)
            startActivity(intent)
        }

    }

    private fun funGet() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            try {
                val response = APIClient.apiService.getOffres()
                if (response.isSuccessful && response.body() != null) {
                    recyclerView.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 1)
                        adapter = MyAdapter(response.body()!!, this@MainActivity)
                    }

                } else {
                    Log.e("Error", response.message())
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    override fun OnItemClick(position: Int) {

        Toast.makeText(this, "Item " + position + " clicked" , Toast.LENGTH_LONG).show()

    }
}