package com.example.blackbrickstestquest

import kotlin.random.Random


private const val GAME_WIDTH = 5
private const val GAME_HEIGHT = 5
private const val ALIVE_CELL = "O   "
private const val DEAD_CELL = "X   "
private const val ROUND_DELAY: Long = 250

class Game {

    private val livingCellsCordsAtStart = generateStartLivingCells()

    private val board = Board(width = GAME_WIDTH, height = GAME_HEIGHT, livingCellsCordsAtStart)

    private fun generateStartLivingCells(): Array<Pair<Int,Int>> {
        val countOfLivingCells = Random.nextInt(25)

        var startLivingCells : Array<Pair<Int,Int>> = emptyArray()

        for(randomCord in 0..countOfLivingCells) {
            val cordX = Random.nextInt(5)
            val cordY = Random.nextInt(5)

            val cord = cordX to cordY

            startLivingCells += cord
        }
        return startLivingCells
    }

    fun playGame() {

        printCurrentState(board)


        while(isNextGenerationHasAliveCells()) {
            board.generateNextState()
            Thread.sleep(ROUND_DELAY)
            printCurrentState(board)
        }
    }

    private fun printCurrentState(board: Board) {

        val boardCells = board.cells

        for(cellX in boardCells.indices) {
            for (cellY in boardCells[cellX].indices) {
                if (boardCells[cellX][cellY].isAlive) {
                    print(ALIVE_CELL)
                } else {
                    print(DEAD_CELL)
                }
            }
            println()
        }
        println()
    }

    private fun isNextGenerationHasAliveCells(): Boolean {
        return board.getCountLivingCells() > 0
    }
}