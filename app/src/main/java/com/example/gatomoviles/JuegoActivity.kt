package com.example.gatomoviles

import android.app.GameManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class JuegoActivity : AppCompatActivity(){

    private lateinit var gameManager: com.example.gatomoviles.GameManager
    private lateinit var uno: TextView
    private lateinit var dos: TextView
    private lateinit var tres: TextView
    private lateinit var cuatro: TextView
    private lateinit var cinco: TextView
    private lateinit var seis: TextView
    private lateinit var siete: TextView
    private lateinit var ocho: TextView
    private lateinit var nueve: TextView
    private lateinit var btnNuevo: Button
    private lateinit var puntos1: TextView
    private lateinit var puntos2: TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        gameManager = GameManager()

        uno = findViewById(R.id.uno)
        dos = findViewById(R.id.dos)
        tres = findViewById(R.id.tres)
        cuatro = findViewById(R.id.cuatro)
        cinco = findViewById(R.id.cinco)
        seis = findViewById(R.id.seis)
        siete = findViewById(R.id.siete)
        ocho = findViewById(R.id.ocho)
        nueve = findViewById(R.id.nueve)
        btnNuevo = findViewById(R.id.btnNuevo)
        puntos1 = findViewById(R.id.puntos_j1)
        puntos2 = findViewById(R.id.puntos_j2)

        uno.setOnClickListener { onBoxClicked(uno, posicion(0, 0)) }
        dos.setOnClickListener { onBoxClicked(dos, posicion(0, 1)) }
        tres.setOnClickListener { onBoxClicked(tres, posicion(0, 2)) }
        cuatro.setOnClickListener { onBoxClicked(cuatro, posicion(1, 0)) }
        cinco.setOnClickListener { onBoxClicked(cinco, posicion(1, 1)) }
        seis.setOnClickListener { onBoxClicked(seis, posicion(1, 2)) }
        siete.setOnClickListener { onBoxClicked(siete, posicion(2, 0)) }
        ocho.setOnClickListener { onBoxClicked(ocho, posicion(2, 1)) }
        nueve.setOnClickListener { onBoxClicked(nueve, posicion(2, 2)) }

        btnNuevo.setOnClickListener {
            btnNuevo.visibility = View.GONE
            gameManager.reset()
            resetboxes()
        }

        updatePoints()
    }

    private fun updatePoints() {
        puntos1.text = "Jugador X: ${gameManager.puntos1}"
        puntos2.text = "Jugador O: ${gameManager.puntos2}"
    }


    private fun resetboxes() {
        uno.text = ""
        dos.text = ""
        tres.text = ""
        cuatro.text = ""
        cinco.text = ""
        seis.text = ""
        siete.text = ""
        ocho.text = ""
        nueve.text = ""
        uno.background = null
        dos.background = null
        tres.background = null
        cuatro.background = null
        cinco.background = null
        seis.background = null
        siete.background = null
        ocho.background = null
        nueve.background = null
        uno.isEnabled = true
        dos.isEnabled = true
        tres.isEnabled = true
        cuatro.isEnabled = true
        cinco.isEnabled = true
        seis.isEnabled = true
        siete.isEnabled = true
        ocho.isEnabled = true
        nueve.isEnabled = true
    }

    private fun onBoxClicked(box: TextView, position: posicion) {
        if (box.text.isEmpty()) {
            box.text = gameManager.currentPlayerMark
            val lineaGanar = gameManager.makeMove(position)
            if (lineaGanar != null) {
                updatePoints()
                disableBoxes()
                btnNuevo.visibility = View.VISIBLE
                showWinner(lineaGanar)
            }
        }
    }

    private fun disableBoxes() {
        uno.isEnabled = false
        dos.isEnabled = false
        tres.isEnabled = false
        cuatro.isEnabled = false
        cinco.isEnabled = false
        seis.isEnabled = false
        siete.isEnabled = false
        ocho.isEnabled = false
        nueve.isEnabled = false
    }

    private fun showWinner(winningLine: lineaGanar) {
        val (winningBoxes, background) = when (winningLine) {
            lineaGanar.ROW_0 -> Pair(listOf(uno, dos, tres), R.drawable.horizontal_linea)
            lineaGanar.ROW_1 -> Pair(listOf(cuatro, cinco, seis), R.drawable.horizontal_linea)
            lineaGanar.ROW_2 -> Pair(listOf(siete, ocho, nueve), R.drawable.horizontal_linea)
            lineaGanar.COLUMN_0 -> Pair(listOf(uno, cuatro, siete), R.drawable.vertical_linea)
            lineaGanar.COLUMN_1 -> Pair(listOf(dos, cinco, ocho), R.drawable.vertical_linea)
            lineaGanar.COLUMN_2 -> Pair(listOf(tres, seis, nueve), R.drawable.vertical_linea)
            lineaGanar.DIAGONAL_LEFT -> Pair(listOf(uno, cinco, nueve),
                R.drawable.diagonal_izquierda_line
            )
            lineaGanar.DIAGONAL_RIGHT -> Pair(listOf(tres, cinco, siete),
                R.drawable.diagonal_derecha_linea
            )
        }

        winningBoxes.forEach { box ->
            box.background = ContextCompat.getDrawable(GameActivity@ this, background)


        }
    }

}