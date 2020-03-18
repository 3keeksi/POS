/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euler59;

import java.util.concurrent.Callable;

/**
 * XORWorker does a decryption with the key it is given
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
		// go through every 
		for (Integer ch : XORLauncher.input) {
			// xor the current integer and add it to the complete sum
			int XORed = ch ^ key[count % 3];
			count++;
			sum += XORed;
			// also add it to the String
			converted += (char) XORed;
		}

		count = 0;
		// count the known words in the string
		for (String string : converted.split(" ")) {
			if (XORLauncher.words.contains(string)) {
				count++;
			}
		}
		// my criteria to make it count is that it knows at least half of the words
		if (count >= converted.split(" ").length * 0.5) {
			return sum;
		}
		return 0;
	}

}
