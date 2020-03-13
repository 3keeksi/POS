/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler59;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author crether
 */
public class IOHandler {

	public static final Path input = Paths.get(System.getProperty("user.dir"), "src", "euler59", "input.txt");
	public static final Path words = Paths.get(System.getProperty("user.dir"), "src", "euler59", "words.txt");

	public static List<Integer> getInput() throws FileNotFoundException {
		List<Integer> bytes = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(input.toFile()));
		br.lines()
				.map(l -> l.split(","))
				.forEach(i -> Arrays.stream(i)
				.map(Integer::parseInt)
				.forEach(bytes::add));
		return bytes;
	}

	public static List<String> getWords() throws FileNotFoundException {
		List<String> list;
		BufferedReader br = new BufferedReader(new FileReader(words.toFile()));

		list = br.lines()
				.skip(1)
				.collect(Collectors.toList());

		return list;
	}

}
