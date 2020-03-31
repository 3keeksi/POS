/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io_access;

import beans.FFItem;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Belma
 */
public class IO_Access {
    public static List<FFItem> loadData() throws FileNotFoundException, IOException{
        List<FFItem> returns = new ArrayList<>();
        String filename = System.getProperty("user.dir") + File.separator + "src" + File.separator + "res" + File.separator + "data.csv";
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        //product;energy;saturates;sugars;salt
        while((line = br.readLine()) != null){
            String[] parts = line.split(";");
            System.out.println(parts[1]);
            try {
                int energy = Integer.parseInt(parts[1]);
                int saturates = Integer.parseInt(parts[2]);
                int sugars = Integer.parseInt(parts[3]);
                int salt = Integer.parseInt(parts[4]);
                returns.add(new FFItem(parts[0], energy, saturates, sugars, salt));
            }catch(NumberFormatException e) {
                throw e;
            }
        }
        return null;
    }
}
