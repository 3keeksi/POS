/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package password.bl;

import java.util.Random;

/**
 *
 * @author Denis Imeri
 */
public class PasswordBL {
    private String passwd = "";
    private int strength;
    private String alphabet = "";

    public PasswordBL() {
        alphabet = "";
        for (int i = 0; i < 26; i++) {
            alphabet += (char)('A'+i);
        }
        for (int i = 0; i < 26; i++) {
            alphabet += (char)('a'+i);
        }
        for (int i = 0; i < 10; i++) {
            alphabet += (char)('0'+i);
        }
    }
    
    public void generatePasswd(int length){
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            passwd += alphabet.charAt(rand.nextInt(alphabet.length()));
        }
    }
    
    public void generatePasswd(int length, int strength) {
        do{
            generatePasswd(length);
            checkPasswdStrength();
        }while(this.strength < strength);
    }
    
    public void checkPasswdStrength(){
        strength = 0;
        int n = passwd.length();
        int numCL = getNumberOfChars('A', 'Z');
        int numLL = getNumberOfChars('a', 'z');
        int numDL = getNumberOfChars('0', '9');
        int conUpper = getConsecutiveChars('A', 'Z');
        int conLower = getConsecutiveChars('a', 'z');
        int conDigit = getConsecutiveChars('0', '9');
        
        strength+= (n*4)
                + (n - numLL) * 2
                + (n - numCL) * 2
                + numDL * 4
                - conLower == n ? n : 0
                - conUpper == n ? (n*2) : 0
                - conDigit == n ? n : 0
                - conLower == n ? n : 0
                - conUpper == n ? (n*2) : 0
                - conDigit == n ? n : 0;
    }
    
    public int getNumberOfChars(char cStart, char cEnd){
        int num = 0;
        for (char value : passwd.toCharArray()) {
            if(cStart <= value && value <= cEnd){
                num++;
            }
        }
        return num;
    }
    
    public int getConsecutiveChars(char cStart, char cEnd){
        int num = 0;
        for (int i = 0; i < passwd.length()-1; i++) {
            char c1 = passwd.charAt(i);
            char c2 = passwd.charAt(i+1);
            if((cStart <= c1 && c1 <= cEnd) && (cStart <= c2 && c2 <= cEnd)){
                num++;
            }
        }
        return num;
    }

    @Override
    public String toString() {
        return String.format("Password \"%s\" has strength %d", passwd, strength);
    }
    
    

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getSterngth() {
        return strength;
    }

    public void setSterngth(int strength) {
        this.strength = strength;
    }
    
    
}
