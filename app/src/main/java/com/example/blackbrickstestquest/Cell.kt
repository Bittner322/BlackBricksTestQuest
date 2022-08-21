package com.example.blackbrickstestquest


/*
Правило 1: Любая живая клетка с менее чем двумя живыми соседями умирает.
Правило 2: Любая живая клетка с двумя-тремя живыми соседями живет до следующего поколения.
Правило 3: Любая живая клетка с более чем тремя живыми соседями умирает.
Правило 4: Любая мертвая клетка, имеющая ровно три живых соседа, становится живой клеткой.
*/
class Cell(var isAlive: Boolean) {

    private var neighbours: Array<Cell> = emptyArray()

    fun generateNextState(): Boolean {

        val livingCells = neighbours.filter { it.isAlive }

        return if (isAlive) {
            livingCells.size in 2..3
        } else {
            livingCells.size == 3
        }
    }

    fun applyNextGenerationState(isAlive: Boolean) {
        this.isAlive = isAlive
    }

    fun setNeighbours(neighbours: Array<Cell>) {
        this.neighbours = neighbours
    }
}