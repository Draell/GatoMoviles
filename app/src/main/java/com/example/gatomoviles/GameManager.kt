package com.example.gatomoviles

class GameManager {

    private var currentPlayer = 1
    var puntos1 = 0
    var puntos2 = 0

    val currentPlayerMark: String
        get() {
            return if (currentPlayer == 1) "X" else "O"
        }

    private var state = arrayOf( // 2D Array
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun makeMove(position: posicion): lineaGanar? {
        state[position.row][position.column] = currentPlayer
        val winningLine = hasGameEnded()

        if (winningLine == null) {
            currentPlayer = 3 - currentPlayer
        } else {
            when (currentPlayer) {
                1 -> puntos1++
                2 -> puntos2++
            }
        }

        return winningLine
    }

    fun reset() {
        state = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        currentPlayer = 1
    }

    private fun hasGameEnded(): lineaGanar? {
        if (state[0][0] == currentPlayer && state[0][1] == currentPlayer && state[0][2] == currentPlayer) {
            return lineaGanar.ROW_0
        } else if (state[1][0] == currentPlayer && state[1][1] == currentPlayer && state[1][2] == currentPlayer) {
            return lineaGanar.ROW_1
        } else if (state[2][0] == currentPlayer && state[2][1] == currentPlayer && state[2][2] == currentPlayer) {
            return lineaGanar.ROW_2
        } else if (state[0][0] == currentPlayer && state[1][0] == currentPlayer && state[2][0] == currentPlayer) {
            return lineaGanar.COLUMN_0
        } else if (state[0][1] == currentPlayer && state[1][1] == currentPlayer && state[2][1] == currentPlayer) {
            return lineaGanar.COLUMN_1
        } else if (state[0][2] == currentPlayer && state[1][2] == currentPlayer && state[2][2] == currentPlayer) {
            return lineaGanar.COLUMN_2
        } else if (state[0][0] == currentPlayer && state[1][1] == currentPlayer && state[2][2] == currentPlayer) {
            return lineaGanar.DIAGONAL_LEFT
        } else if (state[0][2] == currentPlayer && state[1][1] == currentPlayer && state[2][0] == currentPlayer) {
            return lineaGanar.DIAGONAL_RIGHT
        }
        return null
    }

    private fun hasGameEndedV2(): Boolean {
        state.forEach { row ->
            val hasWon = row.all { player -> player == currentPlayer }
            if (hasWon) return true
        }

        for (i: Int in state.indices) {
            val hasWon = state[i].all { player -> player == currentPlayer }
            if (hasWon) return true
        }

        for (i in state.indices) {
            if (state[i][i] != currentPlayer) return false
        }

        for (i in state.size until 0) {
            if (state[i][i] != currentPlayer) return false
        }

        return true
    }
}