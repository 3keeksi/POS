/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import beans.Customer;
import beans.Location;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author crether
 */
public class FileAccess {
    private static String filebase = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "res" + File.separator;

    public static List<Location> loadLocations(String filename) throws FileNotFoundException, IOException {
        List<Location> list = new ArrayList<>();
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            Location location = new Location(Integer.parseInt(parts[0]), parts[2], parts[1]);
            list.add(location);
        }

        return list;
    }

    public static List<Customer> loadCustomers(String filename) throws FileNotFoundException, IOException {
        List<Customer> list = new ArrayList<>();

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        //location_id;firstname;lastname;gender;birthdate
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");

            int id = Integer.parseInt(parts[0]);
            char gender;
            if (parts[3].equalsIgnoreCase("Male")) {
                gender = 'M';
            } else {
                gender = 'F';
            }
            String[] dateParts = parts[4].split("/");
            int month = Integer.parseInt(dateParts[0]);
            int day = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);
            LocalDate date = LocalDate.of(year, month, day);
            list.add(new Customer(id, parts[1], parts[2], gender, date, Customer.getAgeDifference(date)));
        }

        return list;
    }
    
    public static void generateCustomerInvitation(List<Customer> list, List<String> countries) {
        for (Customer customer : list) {
            String filename = filebase + customer.getLastname() + "_" + customer.getFirstname();
        }
    }
}
