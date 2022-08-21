package com.example.blackbrickstestquest


class Board(private val width: Int, private val height: Int, initialLiveCells: Array<Pair<Int,Int>>) {

    private var round = 0

    val cells: Array<Array<Cell>> = Array(width) { y ->
        Array(height) { x ->
            Cell(isAlive = Pair(x, y) in initialLiveCells)
        }
    }

    init {
        initNeighbours()
    }

    private fun initNeighbours() {
        for (neighbourX in cells.indices) {
            for (neighbourY in cells[neighbourX].indices) {
                cells[neighbourX][neighbourY].setNeighbours(getNeighboursForCell(neighbourX,neighbourY))
            }
        }
    }

    fun getCountLivingCells(): Int {

        var livingCellsCounter = 0

        for (cellX in cells.indices) {
            for (cellY in cells[cellX].indices) {
                if(cells[cellX][cellY].isAlive) {
                    livingCellsCounter += 1
                }
            }
        }

        return livingCellsCounter
    }

    fun generateNextState() {

        val nextCellStates: Array<Array<Boolean>> = Array(width) { Array(height) { false } }


        for (cellX in cells.indices) {
            for (cellY in cells[cellX].indices) {
                nextCellStates[cellX][cellY] = cells[cellX][cellY].generateNextState()
            }
        }

        for (cellX in nextCellStates.indices) {
            for (cellY in nextCellStates[cellX].indices) {
                cells[cellX][cellY].applyNextGenerationState(nextCellStates[cellX][cellY])
            }
        }

        round += 1
    }

    private fun getNeighboursForCell(x: Int, y: Int): Array<Cell> {

        var neighbors: Array<Cell> = emptyArray()

        for(neighborX in x - 1..x + 1) {
            for (neighborY in y - 1..y + 1) {

                val isNeighbourNotCurrentCell = neighborX != x || neighborY != y
                val isNeighbourInsideBoard = neighborX in 0 until width && neighborY in 0 until height

                if(isNeighbourNotCurrentCell && isNeighbourInsideBoard) {
                    neighbors += cells[neighborX][neighborY]
                }
            }
        }
        return neighbors
    }
}