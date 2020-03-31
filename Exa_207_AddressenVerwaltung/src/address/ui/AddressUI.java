/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package address.ui;

import address.bl.Address;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Denis Imeri
 */
public class AddressUI {

    public static void main(String[] args) {
        AddressUI ui = new AddressUI();
        Address add = ui.createAddress();
        System.out.println(add);
        ui.changeAddress(add);
        System.out.println(add);
    }
    
    
    /**
     * Returns an Address object that can be printed out
     * @return 
     */
    public Address createAddress() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter street: ");
        String street = scan.nextLine();
        System.out.print("Enter number: ");
        int number;
        try {
            number = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Sorry, only Numbers are allowed.\n"
                    + "Please try again: ");
            scan.nextLine();
            number = scan.nextInt();
        }

        scan.nextLine();
        System.out.print("Enter city: ");
        String city = scan.nextLine();
        System.out.print("Enter zipcode: ");
        int zipCode;
        try {
            zipCode = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.print("Sorry, only Numbers are allowed.\n"
                    + "Please try again: ");
            scan.nextLine();
            zipCode = scan.nextInt();
        }
        Address add = new Address(street, number, city, zipCode);
        return add;
    }

    public void changeAddress(Address add) {
        Scanner scanner = new Scanner(System.in);
        System.out.format("What would you like to change?\n"
                + "(A) street: %s\n"
                + "(B) number: %d\n"
                + "(C) city: %s\n"
                + "(D) zipCode: %d\n"
                + "(Q) You don't want to change anything\n"
                + "", add.getStreet(), add.getNumber(), add.getCity(), add.getZipCode());
        System.out.print("Choice: ");
        char answer = scanner.next().charAt(0);
        if (!(answer == 'q' || answer == 'Q')) {
            while (!((65 <= answer && answer <= 68) || (97 <= answer && answer <= 100))) {
                System.out.print("Wrong input.\n"
                        + "Type in again: ");
                answer = scanner.next().charAt(0);
            }
            System.out.print("Enter new value: ");
            scanner.nextLine();
            switch (answer) {
                case 'A':
                case 'a':
                    add.setStreet(scanner.nextLine());
                    break;
                case 'B':
                case 'b':
                    try {
                        add.setNumber(scanner.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.print("Sorry, only Numbers are allowed.\n"
                                + "Please try again: ");
                        scanner.nextLine();
                        add.setNumber(scanner.nextInt());
                    }
                    break;
                case 'C':
                case 'c':
                    add.setCity(scanner.nextLine());
                    break;
                case 'D':
                case 'd':
                    try {
                        add.setZipCode(scanner.nextInt());
                    } catch (InputMismatchException e) {
                        System.out.print("Sorry, only Numbers are allowed.\n"
                                + "Please try again: ");
                        scanner.nextLine();
                        add.setZipCode(scanner.nextInt());
                    }
                    break;
                default:
                    System.out.println("So you don't want to change anything. That's ok too.");
                    break;
            }
        }
    }
}
