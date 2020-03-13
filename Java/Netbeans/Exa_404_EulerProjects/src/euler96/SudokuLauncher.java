/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler96;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class SudokuLauncher {

	public static List<Integer[][]> boards;

	public static void solveSudokus() throws IOException {
		boards = IOHandler.getInput();
		ExecutorService pool = Executors.newFixedThreadPool(4);
		CompletionService<Integer> service = new ExecutorCompletionService<>(pool);
		for (Integer[][] board : boards) {
			service.submit(new SudokuWorker(board));
		}
		pool.shutdown();

		Integer sum = 0;
		while (!pool.isTerminated()) {
			try {
				Future<Integer> future = service.take();
				Integer val = future.get();
				sum += val;
			} catch (InterruptedException | ExecutionException ex) {
				Logger.getLogger(SudokuLauncher.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		System.out.println(sum);
	}

	public static void main(String[] args) {
		try {
			solveSudokus();
		} catch (IOException ex) {
			Logger.getLogger(SudokuLauncher.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
