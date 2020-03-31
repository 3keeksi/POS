/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lottozahlen;

import java.util.Random;

/**
 *
 * @author Denis Imeri
 */
public class LottoNumbers {
        private int[] numbers = new int[6];
        private int additionalNumber;
        private int temp;
        
        public LottoNumbers(){
            Random rand = new Random();
            boolean abfrage;
            for(int i = 0;i<6;i++){
                do{
                    abfrage = false;
                    numbers[i] = rand.nextInt(45)+1;
                    for(int j = 0;j<6;j++){
                        if(i!=j){
                            if(numbers[i] == numbers[j]){
                                abfrage = true;
                            }
                        }
                    }
                }while(abfrage);
            }
            do{
                abfrage = false;
                additionalNumber = rand.nextInt(45)+1;
                for(int i = 0;i<6;i++){
                    if(numbers[i] == additionalNumber){
                        abfrage = true;
                        break;
                    }
                }
                
            }while(abfrage);
        }
        
        public static void main(String[] args) {
            LottoNumbers lotto = new LottoNumbers();
            lotto.sortNumbers();
            System.out.println(lotto.toString());
        }
        
        public void sortNumbers(){
            for(int i = 0;i<6;i++){
                for(int j = i+1;j<6;j++){
                    if(numbers[i]>numbers[j]){
                        temp = numbers[i];
                        numbers[i] = numbers[j];
                        numbers[j] = temp;
                    }
                }
            }
        }
        @Override
        public String toString(){
            String string = "";
            for(int i = 0; i<5 ; i++){
                string += String.format("%d, ", numbers[i]);
            }
            string += String.format("%d - Zusatzzahl: %d", numbers[5], additionalNumber);
            return string;
        }
}
