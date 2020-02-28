package conprod;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author crether
 */
public class Stack {
    private int[] values;
    private int tos;
    
    public Stack(int i){
        values = new int[i];
    }
    
    public boolean isFull() {
        return values.length == tos; 
    }
    
    public boolean isEmpty() {
        return tos == 0;
    }
    
    public void push(int value) {
        if (isFull()) throw new RuntimeException("Stack is full!");
        
        values[tos++] = value;
    }
    
    public int pop() {
        if(isEmpty()) throw new RuntimeException("Stack is empty!");
        return values[--tos];
    }

    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i<tos;i++) {
            sb.append(values[i]+",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        System.out.println(stack);
        stack.push(11);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
    }
}
