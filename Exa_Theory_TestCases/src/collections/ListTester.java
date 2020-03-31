/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package collections;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Denis Imeri
 */
public class ListTester {
    
    private List<Integer> myList = new ArrayList<>();
    
    public void initList(){
        // add to myList the value: 10, 20, 30, 40, .... , 100
        for (int i = 1; i <= 20; i++) {
            myList.add(i*10);  // <- Autoboxing = instant cast von int => Integer
            //myList.add(Integer(i*10)) <= ganz richtiger Weg
        }
    }
    
    public void insertAtBeginning(int value){
        myList.add(1, value);
        myList.remove(3); //entfernt 40
        //myList.remove(new Integer(40)); <- removed den ersten 40er
    }
    
    public void printList() {
        for (int i = 0; i < myList.size(); i++) {
            int value = myList.get(i);  // <- Unboxing
            System.out.print(value + ", ");
        }
        System.out.println("");
    }
    
    public static void main(String[] args) {
        ListTester tester = new ListTester();
        tester.initList();
        tester.printList();
        tester.insertAtBeginning(9999);
        tester.printList();
    }
    
}
