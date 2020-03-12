/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler39;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author crether
 */
public class TriangleLauncher {

    public void performTriangleCalculation() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService<Set<Triple>> service = new ExecutorCompletionService<>(pool);
        for (int p = 0; p <= 1000; p++) {
            service.submit(new TriangleWorker(p));
        }
        pool.shutdown();
        Set<Triple> maxTriple = null;
        while (!pool.isTerminated()) {
            try {
                Future<Set<Triple>> future = service.take();
                Set<Triple> result = future.get();
                if (maxTriple == null || result.size() > maxTriple.size()) {
                    maxTriple = result;
                }
                if (result.size() > 0) {
                    System.out.println(new ArrayList<>(result).get(0) + "-->" + result.size());
                }
            } catch (InterruptedException | ExecutionException ex) {
                System.out.println(ex);
            }
        }
        for (Triple triple : maxTriple) {
            System.out.println(triple);
        }
    }

    public static void main(String[] args) {
        new TriangleLauncher().performTriangleCalculation();
    }

}
