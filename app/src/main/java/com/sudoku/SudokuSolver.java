package com.sudoku;

public class SudokuSolver {
    /**
     * From:
     * http://dannylesnik.blogspot.com/2011/01/hibernate-collection-mapping.html
     */

    private static final int UNASSIGNED = 0;

    public boolean solve(int row, int col, Integer [][] cells) {

        if (row == 9) {
            row = 0;
            if (++col == 9) {
                return true;
            }
        }
        if (cells[row][col] != UNASSIGNED) // skip filled cells
            return solve(row + 1, col, cells);
        for (int val = 1; val <= 9; ++val) {
            if (isSafe(row, col, val, cells)) {
                cells[row][col] = val;
                if (solve(row + 1, col, cells))
                    return true;
            }
        }
        cells[row][col] = UNASSIGNED; // reset on backtrack
        return false;
    }
    public boolean isSafe(int row, int col, int val, Integer [][] cells) {
        for (int k = 0; k < 9; ++k)
            // row
            if (val == cells[k][col])
                return false;
        for (int k = 0; k < 9; ++k)
            // col
            if (val == cells[row][k])
                return false;
        int boxRowOffset = (row / 3) * 3;
        int boxColOffset = (col / 3) * 3;
        for (int k = 0; k < 3; ++k)
            // box
            for (int m = 0; m < 3; ++m)
                if (val == cells[boxRowOffset + k][boxColOffset + m])
                    return false;
        return true;
    }
}
