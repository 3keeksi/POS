/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorter;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Denis Imeri
 */
public class Sorter {

    private int[] numbers;
    private long time;

    public Sorter(int sizeOfArray) {
        Random rand = new Random();

        numbers = new int[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            numbers[i] = rand.nextInt(sizeOfArray - 1) + 1;
        }
    }

    public static void main(String[] args) {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.print("\nLänge des Feldes: ");

            int size = scan.nextInt();
            Sorter sort = new Sorter(size);
            String txt = "";
            for (int i = 0; i < sort.numbers.length; i++) {
                if (i % 10 == 0) {
                    txt += "\n";
                }
                txt += sort.numbers[i] + ", ";
            }
            System.out.println(txt);

            System.out.print("\n(1)Selection-Sort\n"
                    + "(2)Insertion-Sort\n"
                    + "(3)Bubble-Sort\n"
                    + "(4)Quick-Sort\n"
                    + "Wählen sie aus: ");
            int type = scan.nextInt();
            final long timeStart = System.currentTimeMillis();
            switch (type) {
                case 1:
                    sort.selectionSort();
                    break;
                case 2:
                    sort.insertionSort();
                    break;
                case 3:
                    sort.bubbleSort();
                    break;
                case 4:
                    sort.quickSort(0, size - 1);
                default:
                    System.out.println("Falsche Eingabe. Neustarten");
                    break;
            }
            final long timeEnd = System.currentTimeMillis();
            sort.time = timeEnd - timeStart;
            System.out.println(sort.toString());
        }
    }

    public void selectionSort() {

        for (int i = 0; i < numbers.length; i++) {
            int index = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[index] > numbers[j]) {
                    index = j;
                }
            }
            int temp = numbers[i];
            numbers[i] = numbers[index];
            numbers[index] = temp;
        }
    }

    public void insertionSort() {
        int min, j;
        for (int i = 1; i < numbers.length; ++i) {
            min = numbers[i];
            j = i - 1;

            while (j >= 0 && numbers[j] > min) {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = min;
        }
    }

    public void bubbleSort() {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    public void quickSort(int l, int r) {
        int p, i, k, temp;
        if (r > l) {
            p = numbers[r];
            i = l - 1;
            k = r;
            do {
                do {
                    i++;
                } while (numbers[i] >= p);
                do {
                    k--;
                } while (numbers[k] <= p);
                if (i < k) {
                    temp = numbers[i];
                    numbers[i] = numbers[r];
                    numbers[r] = temp;
                }
                else{
                    break;
                }
            } while (i > k);
            quickSort(l, i-1);
            quickSort(i+1, r);
        }
    }

    @Override
    public String toString() {
        String txt = "";
        for (int i = 0; i < numbers.length; i++) {
            if (i % 10 == 0) {
                txt += "\n";
            }
            txt += numbers[i] + ", ";
        }
        txt += "\nDauer für das Sortieren: " + time + "ms";
        return txt;
    }
}
