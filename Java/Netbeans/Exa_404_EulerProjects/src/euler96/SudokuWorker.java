/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler96;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 * SudokuWorker implements the solving of the Sudoku by using backtracking
 *
 * @author crether
 */
public class SudokuWorker implements Callable<Integer> {

    private Integer[][] board;
    private boolean finished = false;

    public SudokuWorker(Integer[][] board) {
        this.board = board;
    }

    @Override
    public Integer call() throws Exception {
        solve(0);
        //get the first three numbers
        Integer sum = board[0][0] * 100 + board[0][1] * 10 + board[0][2];
        return sum;
    }

    /**
     * the recursive method which starts at the specified position to be faster
     * (instead of going through everything)
     *
     * @param startY specifies the start point for the y coordinate
     */
    public void solve(int startY) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 0) {
                    for (int i = 1; i < 10; i++) {
                        //check if it can place
                        if (isPossible(y, x, i)) {
                            // sets the number
                            board[y][x] = i;
                            solve(y);
                            if (this.finished)
                                return;
                            // sets it to 0 again because the number before clearly doesn't work and through that it "backtracks"
                            board[y][x] = 0;
                        }
                    }
                    return;
                }
            }
        }
        // sets finished to true because there are no more 0's in the board
        this.finished = true;
    }

    /**
     * checks if the number is possible at that position
     *
     * @param y the y coordinate to check
     * @param x the x coordinate to check
     * @param num the number in question
     * @return true if it is possible, false if it isn't
     */
    public boolean isPossible(int y, int x, int num) {
        // checks the row
        for (int xMov = 0; xMov < board[y].length; xMov++) {
            if (board[y][xMov] == num)
                return false;
        }
        // checks the column
        for (int yMov = 0; yMov < board.length; yMov++) {
            if (board[yMov][x] == num)
                return false;
        }

        // calculate the first positions of the box where its in
        int startY = Math.floorDiv(y, 3) * 3;
        int startX = Math.floorDiv(x, 3) * 3;
        for (int y1 = 0; y1 < 3; y1++) {
            for (int x1 = 0; x1 < 3; x1++) {
                if (board[startY + y1][startX + x1] == num)
                    return false;
            }
        }
        return true;
    }

}
