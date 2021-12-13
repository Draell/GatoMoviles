package com.example.gatomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

lateinit var btnNuevo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNuevo = findViewById(R.id.btn_Nuevo_Juego)
        btnNuevo.setOnClickListener{
            val intent = Intent(this, JuegoActivity::class.java)
            startActivity(intent)
        }

    }
}