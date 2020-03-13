/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler59;

import euler39.TriangleWorker;
import euler39.Triple;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author crether
 */
public class XORLauncher {

	public static List<Integer> input = new ArrayList<>();
	public static List<String> words = new ArrayList<>();

	public static void solveXOR() throws FileNotFoundException {
		input = IOHandler.getInput();
		words = IOHandler.getWords();

		ExecutorService pool = Executors.newFixedThreadPool(4);
		CompletionService<Integer> service = new ExecutorCompletionService<>(pool);
		for (int i1 = (int) 'a'; i1 <= (int) 'z'; i1++) {
			for (int i2 = (int) 'a'; i2 <= (int) 'z'; i2++) {
				for (int i3 = (int) 'a'; i3 <= (int) 'z'; i3++) {
					service.submit(new XORWorker(new Integer[]{i1, i2, i3}));
				}
			}
		}
		pool.shutdown();

		while (!pool.isTerminated()) {
			try {
				Future<Integer> future = service.take();
				Integer entry = future.get();
				if (entry > 0) {
					System.out.println(entry + "");
				}
			} catch (InterruptedException | ExecutionException ex) {
				Logger.getLogger(XORLauncher.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void main(String[] args) {
		try {
			XORLauncher.solveXOR();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(XORLauncher.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
