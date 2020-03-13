/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler59;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 *
 * @author crether
 */
public class XORWorker implements Callable<Integer> {

	private Integer[] key;

	public XORWorker(Integer[] key) {
		this.key = key;

	}

	@Override
	public Integer call() throws Exception {
		int count = 0;
		int sum = 0;
		String converted = "";
		for (Integer ch : XORLauncher.input) {
			int XORed = ch ^ key[count % 3];
			count++;
			sum += XORed;
			converted += (char) XORed;
		}

		count = 0;
		int mishaps = 0;
		for (String string : converted.split(" ")) {
			if (XORLauncher.words.contains(string)) {
				count++;
			} else {
				mishaps++;
			}
		}
		if (count >= converted.split(" ").length*0.5) {
			return sum;
		}
		return 0;
	}

}
