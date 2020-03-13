/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler96;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author crether
 */
public class IOHandler {

	private static Path input = Paths.get(System.getProperty("user.dir"), "src", "euler96", "input.txt");

	public static List<Integer[][]> getInput() throws FileNotFoundException, IOException {
		List<Integer[][]> list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(input.toFile()));
		String line = "";
		Integer[][] board;
		final AtomicInteger free = new AtomicInteger(0);

		while ((line = br.readLine()) != null) {
			board = new Integer[9][9];
			for (int i = 0; i < 9; i++) {
				line = br.readLine();
				board[i] = line.chars().map((operand) -> {
					return Integer.parseInt(String.valueOf((char) operand));
				}).boxed().toArray((value) -> {
					if (value == 0) {
						free.addAndGet(1);
					}
					return new Integer[value]; //To change body of generated lambdas, choose Tools | Templates.
				});
			}
			list.add(board);
			free.set(0);
		}
		return list;
	}
}
