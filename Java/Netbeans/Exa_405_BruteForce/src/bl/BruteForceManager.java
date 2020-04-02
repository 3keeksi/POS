/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Person;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 *
 * @author crether
 */
public class BruteForceManager {

    public static Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "passwd_file.csv");

    public static List<Person> loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            return br.lines()
                    .skip(1) // skip the first line of the file
                    .map(Person::new) // I created a constructor which parses a String
                    .collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            System.out.println("There is no input file! (res/passwd_file.csv)");
        }
        return new ArrayList<>();
    }

    public static void crackPasswords() {
        List<Person> persons = loadData();

        // magic to read in a valid number
        Integer input = 1;
        String rangeStr = "Enter a number from 1 to " + persons.size();

        do {
            if (input == null) // if there was an exception before then it was not a Integer
            {
                System.out.println("Invalid number! " + rangeStr);
            } else if (input <= 0) {
                System.out.println("Number is too low! " + rangeStr);
            } else if (input > persons.size()) {
                System.out.println("Number is too high! " + rangeStr);
            } else {
                System.out.println(rangeStr);
            }
            System.out.print("Enter the number of persons you want to crack: ");
            try {
                Scanner s = new Scanner(System.in);
                input = s.nextInt();
                // I call nextLine() because there still is an \n in the buffer after submitting the number
                s.nextLine();
            } catch (InputMismatchException e) {
                // set the variable to null to let the next iteration know it was invalid
                input = null;
            }
            System.out.print("\n");
        } while (input == null || input <= 0 || persons.size() < input);

        System.out.println("==============\n");

        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService<String> service = new ExecutorCompletionService<>(pool);
        long startTime = System.nanoTime();

        // creates a BruteForceWorker for every person
        for (int i = 0; i < input; i++) {
            service.submit(new BruteForceWorker(persons.get(i), i + 1));
        }
        pool.shutdown();

        // variable to count how many passwords actually got cracked
        int count = 0;
        long lastCompletionTime = System.nanoTime();
        while (!pool.isTerminated()) {
            try {
                Future<String> future = service.take();
                String result = future.get();
                lastCompletionTime = System.nanoTime();
                if (result != null) {
                    count++;
                }
            } catch (InterruptedException | ExecutionException ex) {
            }
        }

        System.out.println("\n==============\n"
                + "Result\n"
                + "It took" + BruteForceWorker.getTime(startTime, lastCompletionTime) + "to crack\n"
                + String.format("%d password(s) out of %d", count, input));
    }

    public static void main(String[] args) {
        crackPasswords();
    }
}
