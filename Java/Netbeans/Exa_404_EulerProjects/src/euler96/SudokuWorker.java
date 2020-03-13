/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler96;

import java.util.concurrent.Callable;

/**
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
        solve();
        Integer sum = board[0][0]*100 + board[0][1]*10 + board[0][2];
        return sum;
    }

    public void solve() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 0) {
                    for (int i = 1; i < 10; i++) {
                        if (isPossible(y, x, i)) {
                            board[y][x] = i;
                            solve();
                            if(this.finished) return;
                            board[y][x] = 0;
                        }
                    }
                    return;
                }
            }
        }
        this.finished = true;
    }

    public boolean isPossible(int y, int x, int num) {
        for (int xMov = 0; xMov < board[y].length; xMov++) {
            if (board[y][xMov] == num) {
                return false;
            }
        }
        for (int yMov = 0; yMov < board.length; yMov++) {
            if (board[yMov][x] == num) {
                return false;
            }
        }
        int startY = Math.floorDiv(y, 3) * 3;
        int startX = Math.floorDiv(x, 3) * 3;
        for (int y1 = 0; y1 < 3; y1++) {
            for (int x1 = 0; x1 < 3; x1++) {
                if (board[startY + y1][startX + x1] == num) {
                    return false;
                }
            }
        }
        return true;
    }

}
