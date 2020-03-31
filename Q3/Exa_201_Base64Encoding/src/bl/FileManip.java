/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;

/**
 *
 * @author armin
 */
public class FileManip {
    
    private static String fileBase = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator;
    
    public static void encodeFile(String inFile, String outFile) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(fileBase + inFile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = "";
        String temp = "";
        while((line = br.readLine()) != null) {
            temp+=line + "\n";
        }
        
        String[] lines = temp.split("\n");
        for (int i = 0; i < lines.length - 1; i++) {
            lines[i] += "\n";
        }
        System.out.print(lines[lines.length-1]);
        
        String file = "";
        for (int i = 0; i<lines.length;i++) {
            file += lines[i];
        }
        
        String encoded = Base64.getEncoder().encodeToString(file.getBytes());
        FileWriter fw = new FileWriter(fileBase + outFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(encoded);
        
        br.close();
        bw.close();
    }
    
    public static void decodeFile(String inFile, String outFile) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(fileBase + inFile);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String file = "";
        while((line = br.readLine()) != null) {
            file+=line;
        }
        byte[] decoded = Base64.getDecoder().decode(file.getBytes());
        String decodedString = "";
        for (int i = 0; i < decoded.length; i++) {
            decodedString += (char) decoded[i];
        }
        FileWriter fw = new FileWriter(fileBase + outFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(decodedString);
        
        br.close();
        bw.close();
    }
    
    public static boolean checkIfFilesAreEqual(String file1, String file2) throws FileNotFoundException{
        FileReader fr1 = new FileReader(fileBase + file1);
        FileReader fr2 = new FileReader(fileBase + file2);
        BufferedReader br1 = new BufferedReader(fr1);
        BufferedReader br2 = new BufferedReader(fr2);
        
        return true;
    }
    
}
