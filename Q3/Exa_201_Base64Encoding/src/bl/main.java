/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class main {
    
    public static void main(String[] args) {
        try {
            FileManip.encodeFile("file1.txt", "encode.txt");
            FileManip.decodeFile("encode.txt", "decoded.txt");
            if(FileManip.checkIfFilesAreEqual("file1.txt", "decoded.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Fehler"+ ex.toString());
        } catch (IOException ex) {
            System.out.println("Fehler"+ ex.toString());
        }
    }
    
}
