package at.htlkaindorf.bsp_development.collections;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
    public static void main(String[] args) {
        Set<Integer> lottoNumbers = new TreeSet<>();

        Random rand = new Random();

        do {
            lottoNumbers.add(rand.nextInt(44) + 1);
        } while(lottoNumbers.size() < 6);
    }
}
