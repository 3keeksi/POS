/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler96;

/**
 *
 * @author crether
 */
public class Board {

	private int free;
	private Integer[][] board;

	public Board(int free, Integer[][] board) {
		this.free = free;
		this.board = board;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	public Integer[][] getBoard() {
		return board;
	}

	public void setBoard(Integer[][] board) {
		this.board = board;
	}

}
