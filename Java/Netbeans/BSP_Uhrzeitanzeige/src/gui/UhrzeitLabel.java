/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class UhrzeitLabel extends JLabel implements Runnable {
    Map<Integer, Integer[]> segments = new HashMap<>();
    private Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "coords.csv");
    private int value;
    private List<List<Integer[]>> coords;

    public UhrzeitLabel() {
        this.setOpaque(true);
        this.setBackground(Color.black);
        
        this.value = 0;
        
        segments.put(0, new Integer[]{0, 1, 2, 3, 4, 5});
        segments.put(1, new Integer[]{1, 2});
        segments.put(2, new Integer[]{0, 1, 6, 4, 3});
        segments.put(3, new Integer[]{0, 1, 6, 2, 3});
        segments.put(4, new Integer[]{5, 6, 1, 2});
        segments.put(5, new Integer[]{0, 5, 6, 2, 3});
        segments.put(6, new Integer[]{0, 5, 6, 4, 3, 2});
        segments.put(7, new Integer[]{0, 1, 2});
        segments.put(8, new Integer[]{0, 1, 2, 3, 4, 5, 6});
        segments.put(9, new Integer[]{0, 1, 2, 3, 5, 6});
        
        coords = Files.lines(path).map((t) -> {
            
            return null; //To change body of generated lambdas, choose Tools | Templates.
        }).collect(Collectors.toList());
    }
    
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            paint(this.getGraphics());
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(UhrzeitLabel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        double scaleX = this.getWidth() / 11.;
        double scaleY = this.getHeight() / 18.;
        AffineTransform atrans = new AffineTransform();
        atrans.scale(scaleX, scaleY);
        g2.transform(atrans);
        for(int segment : getSegments(value)) {
            g.setColor(Color.RED);
            g.fillPolygon(getXCoordinatesForSegment(segment), getYCoordinatesForSegment(segment), 6);
            g.drawPolygon(getXCoordinatesForSegment(segment), getYCoordinatesForSegment(segment), 6);
        }
        System.out.println(value+"");
    }
    
    private int[] getXCoordinatesForSegment(int num){
        switch(num){
                case 0:
                    return new int[]{
                        2, 3, 8, 9, 8, 3
                    };
                    
                case 1:
                    return new int[]{
                        9, 8, 8, 9, 10, 10
                    };
                    
                case 2:
                    return new int[]{
                        9, 8, 8, 9, 10, 10
                    };
                    
                case 3:
                    return new int[]{
                        2, 3, 8, 9, 8, 3
                    };
                    
                case 4:
                    return new int[]{
                        2, 1, 1, 2, 3, 3
                    };
                    
                case 5:
                    return new int[]{
                        2, 1, 1, 2, 3, 3
                    };
                    
                case 6:
                    return new int[]{
                        2, 3, 8, 9, 8, 3
                    };
                    
                //oben doppelpunkt
                case -1:
                    return new int[]{
                        4, 4, 7, 7
                    };
                    
                //unten doppelpunkt
                case -2:
                    return new int[]{
                        4, 4, 7, 7
                    };
        }
        
        return null;
    }
    
    private int[] getYCoordinatesForSegment(int num){
        switch(num){
                case 0:
                    return new int[]{
                        2, 3, 3, 2, 1, 1
                    };
                    
                case 1:
                    return new int[]{
                        3, 3, 9, 10, 9, 4
                    };
                    
                case 2:
                    return new int[]{
                        10, 11, 15, 16, 15, 11
                    };
                    
                case 3:
                    return new int[]{
                        16, 17, 17, 16, 15, 15
                    };
                    
                case 4:
                    return new int[]{
                        9, 10, 15, 16, 15, 10
                    };
                    
                case 5:
                    return new int[]{
                        2, 3, 8, 9, 8, 3
                    };
                    
                case 6:
                    return new int[]{
                        9, 10, 10, 9, 8, 8
                    };
                    
                //oben doppelpunkt
                case -1:
                    return new int[]{
                        4, 7, 7, 4
                    };
                
                //unten doppelpunkt
                case -2:
                    return new int[]{
                        11, 14, 14, 11
                    };
        }
        
        return null;
    }
    
    public Integer[] getSegments(int num) {
        return segments.get(num);
    }
    
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        UhrzeitLabel ul = new UhrzeitLabel();
        frame.getContentPane().add(ul);
        frame.setSize(200,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Thread thread = new Thread((Runnable) ul);
        thread.start();
        for (int i = 0; i < 10; i++) {
            ul.setValue(i);
            Thread.sleep(500);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
}
