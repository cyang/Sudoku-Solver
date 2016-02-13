# Sudoku-Solver

![](http://imgur.com/z6DamKbl.jpg)

## What is this repository for?

Sudoku is a puzzle that contains 81 cells, in a 9x9 grid. Each cell may contain a number from 1 to 9; each number can only occur once in each 3x3 zone, row, and column of the grid. The game contains three difficulties, each containing cells wit given numbers in them. The user must fill in the remaining cells while obeying the rules. 

Our solver uses a backtracking algorithm by checking all the possible solutions for the given sudoku. If the solutions assigned do not lead to the solution of Sudoku, the algorithm discards the solutions and rollbacks to the original solutions and retries again in a recursive manner.

## Contributors
* MD Islam ([exp0nge](https://github.com/exp0nge))
* Christopher Yang ([cyang](https://github.com/cyang))
