/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Person;
import java.nio.CharBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author crether
 */
public class BruteForceWorker implements Callable<String> {

    private Person person;
    private int threadNo;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
    private MessageDigest md;

    public BruteForceWorker(Person person, int threadNo) {
        this.person = person;
        this.threadNo = threadNo;
        try {
            // create a MD5 encrypter
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
        }
    }

    @Override
    public String call() throws Exception {
        log("started working now");
        if (md == null) {
            log("not been able to create the MD5 decrypter");
            return "";
        }
        List<Character> chars = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++) {
            chars.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            chars.add(i);
        }
        String passwd = "";
        long startTime = System.nanoTime();
        OUTER:
        for (Character one : chars) {
            for (Character two : chars) {
                for (Character three : chars) {
                    for (Character four : chars) {
                        for (Character five : chars) {
                            passwd = one.toString() + two.toString() + three.toString() + four.toString() + five.toString();
                            if (solve(passwd)) {
                                break OUTER;
                            }

                            passwd = "";
                        }
                    }
                }
            }
        }
        long endTime = System.nanoTime();

        // output the time taken
        if (passwd.length() == 0) {
            log("not cracked the password");
        } else {
            log(String.format("cracked a password: %s in %s",
                    passwd, BruteForceWorker.getTime(startTime, endTime)));
        }
        log("finished");

        return passwd;
    }

    private void log(String msg) {
        System.out.format("[#%d - %s] Worker for %s %s has %s!\n",
                threadNo, DTF.format(LocalTime.now()), person.getFirstName(), person.getLastName(), msg);
    }

    public static String getTime(long startTime, long endTime) {
        // difference between endTime - startTime is the time it took in nanoseconds
        long duration = endTime - startTime;
        
        // calculate the milliseconds
        long ms = duration / 1_000_000L;
        
        //calculate the seconds
        long seconds = ms / 1000L;
        
        //return the seconds/milliseconds
        return String.format("%d seconds / %d milliseconds", seconds, ms);
    }

    public boolean solve(String passwd) {
        // create the string to be hashed
        String toHash = person.getFirstName() + person.getLastName() + passwd;

        byte[] bytes = md.digest(toHash.getBytes());

        String hex = DatatypeConverter.printHexBinary(bytes).toLowerCase();
        if (hex.equals(person.getHash())) {
            return true;
        }

        return false;
    }

}
