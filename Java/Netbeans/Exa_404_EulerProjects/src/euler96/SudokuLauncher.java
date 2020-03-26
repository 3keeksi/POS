/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler96;

import java.io.IOException;
import java.util.ArrayList;
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

    public static void runSudokuCalculation() throws IOException {
        boards = IOHandler.getInput();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService<Integer> service = new ExecutorCompletionService<>(pool);
        // creates a SudokuWorker for every Sudoku board
        List<SudokuWorker> workers = new ArrayList<>();
        for (Integer[][] board : boards) {
            workers.add(new SudokuWorker(board));
        }
        try {
            int sum = pool.invokeAll(workers)
                    .stream()
                    .mapToInt(f -> {
                        try {
                            return f.get();
                        } catch (InterruptedException ex) {
                            return 0;
                        } catch (ExecutionException ex) {
                            return 0;
                        }
                    })
                    .sum();
            System.out.println(sum);
        } catch (InterruptedException ex) {
            Logger.getLogger(SudokuLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        pool.shutdown();
//
//        Integer sum = 0;
//        while (!pool.isTerminated()) {
//            try {
//                // just gets the result of the SudokuWorker and adds it so the sum
//                Future<Integer> future = service.take();
//                Integer val = future.get();
//                sum += val;
//            } catch (InterruptedException | ExecutionException ex) {
//                Logger.getLogger(SudokuLauncher.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        System.out.println(sum);
    }

    public static void main(String[] args) {
        try {
            runSudokuCalculation();
        } catch (IOException ex) {
            Logger.getLogger(SudokuLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
