package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author crether
 */
public class ClockLabel extends JLabel {

    private LocalTime time;
    private Image bg;
    private Path imageUrl = Paths.get(System.getProperty("user.dir"), "src", "res", "clock.png");

    public ClockLabel(LocalTime time) {
        this.time = time;
        this.setOpaque(true);
        this.setBackground(Color.black);
        try {
            this.bg = ImageIO.read(imageUrl.toFile());
        } catch (IOException ex) {
            Logger.getLogger(ClockLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("asd");
        this.setMinimumSize(new Dimension(40,40));
        this.setPreferredSize(new Dimension(40,40));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        double scaleX = this.getWidth() / 80.;
        double scaleY = this.getHeight() / 80.;
        g2.scale(scaleX, scaleY);
        g2.drawImage(bg, 0, 0, this);
                
        if(time == null) return;
        
        int x0 = this.getWidth() / 2;
        int y0 = this.getHeight() / 2;
        int hourLength = 30;
        
        //draw hours
        g2.setColor(Color.BLACK);
        int hours = time.getHour();
        double angle = 360/12 * (hours%12) - 90;
        System.out.println(angle);
        int x1 = (int) Math.round(Math.cos(angle) * hourLength);
        int y1 = (int) Math.round(Math.sin(angle) * hourLength);
//        x1+=x0;
//        y1+=y0;
//        System.out.println("x1: " + x1 + " x0: " + x0);
        System.out.println("y1: " + y1 + " y0: " + y0);
        
        g2.drawLine(x0, y0, x0-x1, y0-y1);
        
        //draw minutes
        
        //draw seconds
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    

}
