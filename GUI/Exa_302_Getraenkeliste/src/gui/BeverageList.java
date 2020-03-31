package gui;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Denis Imeri
 */
public class BeverageList {

    private static final String[] softdrinks = {
        "7up", "Pepsi", "San Pellegrino", "Orange Soda", "Lemon Soda", "Dr. Pepper",
        "Mountain Dew", "Powerrade", "Schweppes", "Murelli", "Clever", "Race"
    };
    private List<String> softdrinkList = new ArrayList<>();

    public BeverageList(int size) {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int index = rand.nextInt(softdrinks.length);
            this.softdrinkList.add(softdrinks[index]);
        }
    }
    
    public int getLength() {
        return this.softdrinkList.size();
    }

    public void printList() {
        System.out.print(">>> ");
        for (int i = 0; i < this.softdrinkList.size(); i++) {
            System.out.print(this.softdrinkList.get(i) + ", ");
        }
        System.out.print("\n");
    }

    public void printOccurance() {
        System.out.println("Occurance of softdrinks: ");
        int[] anz = new int[softdrinks.length];
        for (int i = 0; i < anz.length; i++) {
            anz[i] = 0;
        }

        for (int i = 0; i < anz.length; i++) {
            for (int j = 0; j < this.softdrinkList.size(); j++) {
                if (softdrinks[i].equals(this.softdrinkList.get(j))) {
                    anz[i]++;
                }
            }
            System.out.print(String.format("%s (%d), ", softdrinks[i], anz[i]));
        }
    }

    public void sortList() {
        Collections.sort(this.softdrinkList);
        this.printList();
    }

    public void removeDuplicates() {
        System.out.println("Sorted List wihtout Duplicates");
        this.softdrinkList = new ArrayList(new HashSet(softdrinkList));
        this.sortList();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Anzahl an Softdrinks: ");
        int anz = scan.nextInt();
        if(anz<1){
            System.out.println("Anzahl zu klein");
            return;
        }
        BeverageList bevL = new BeverageList(anz);

        System.out.println(String.format("List of %d softdrinks:", bevL.getLength()));
        bevL.printList();
        
        bevL.printOccurance();
        System.out.println("\nSorted list of softdrinks:");
        bevL.sortList();
        bevL.removeDuplicates();
    }
}
