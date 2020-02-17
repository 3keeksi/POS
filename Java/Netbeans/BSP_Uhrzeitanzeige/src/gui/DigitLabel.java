/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author crether
 */
public class DigitLabel extends JLabel {

    Map<Character, Integer[]> segments = new HashMap<>();
    private Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "coords.csv");
    private Character value;
    private List<List<Integer>> xCoords = new ArrayList<>();
    private List<List<Integer>> yCoords = new ArrayList<>();

    public DigitLabel(){
        this.setOpaque(true);
        this.setBackground(Color.black);

        this.value = '0';
        for (int i = 0; i < 9; i++) {
            xCoords.add(new ArrayList<>());
            yCoords.add(new ArrayList<>());
        }

        segments.put('0', new Integer[]{0, 1, 2, 3, 4, 5});
        segments.put('1', new Integer[]{1, 2});
        segments.put('2', new Integer[]{0, 1, 6, 4, 3});
        segments.put('3', new Integer[]{0, 1, 6, 2, 3});
        segments.put('4', new Integer[]{5, 6, 1, 2});
        segments.put('5', new Integer[]{0, 5, 6, 2, 3});
        segments.put('6', new Integer[]{0, 5, 6, 4, 3, 2});
        segments.put('7', new Integer[]{0, 1, 2});
        segments.put('8', new Integer[]{0, 1, 2, 3, 4, 5, 6});
        segments.put('9', new Integer[]{0, 1, 2, 3, 5, 6});
        segments.put(':', new Integer[]{7, 8}); // doppelpunkte

        try {
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            for (int i = 0; i < lines.size(); i++) {
                String[] get = lines.get(i).split(";");
                for (String get1 : get) {
                    String[] pos = get1.split(",");
                    xCoords.get(i).add(new Integer(pos[0]));
                    yCoords.get(i).add(new Integer(pos[1]));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DigitLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        double scaleX = this.getWidth() / 11.;
        double scaleY = this.getHeight() / 18.;
        g2.scale(scaleX, scaleY);
        g2.setStroke(new BasicStroke(0.2f));
        for (int segment : getSegments(value)) {
            g2.setColor(Color.RED);
            int[] x = xCoords.get(segment).stream().mapToInt(i -> i).toArray();
            int[] y = yCoords.get(segment).stream().mapToInt(i -> i).toArray();
            g2.fillPolygon(x, y, x.length);
            
            g2.setColor(Color.BLACK);
            g2.drawPolygon(x, y, x.length);
        }
    }

    public Integer[] getSegments(Character num) {
        return segments.get(num);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        DigitLabel ul = new DigitLabel();
        frame.getContentPane().add(ul);
        frame.setSize(200, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Thread thread = new Thread((Runnable) ul);
        thread.start();
        for (int i = 0; i < 10; i++) {
            ul.setValue((""+i).charAt(0));
            Thread.sleep(2000);
        }
        ul.setValue(':');
    }

    public int getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }
    
    

}
