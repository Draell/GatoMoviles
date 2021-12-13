package com.example.gatomoviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

lateinit var btnNuevoJuego: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNuevoJuego = findViewById(R.id.btn_Nuevo_Juego)
        btnNuevoJuego.setOnClickListener{
            val intent = Intent(MainActivity@this, JuegoActivity::class.java)
            startActivity(intent)
        }

    }
}