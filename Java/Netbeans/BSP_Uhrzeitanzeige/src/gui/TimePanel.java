/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Dimension2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author crether
 */
public class TimePanel extends JPanel implements Runnable {

    private ZoneId zone;
    private LocalTime time;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
    private DigitLabel[] labels = new DigitLabel[8];
    private static final int TIMEZONE_WIDTH = 200;
    TimePanel panel = this;

    public TimePanel() {
//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent ce) {
//                ((TimePanel)ce.getComponent()).resize();
//            }
//        });
    }

    public void init(boolean local) {
        int width = 0;
        if (local) {
            JLabel label = new JLabel("Lokale Zeit");
            label.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
            label.setFont(new Font("Arial", Font.PLAIN, 26));
            this.add(label);
        } else {
            // add combobox
        }
        for (int i = 0; i < labels.length; i++) {
            DigitLabel label = new DigitLabel();
            labels[i] = label;
            this.add(label);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            time = LocalTime.now();
            char[] chars = DTF.format(time).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                labels[i].setValue(chars[i]);
                labels[i].repaint();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void resize() {
        Component[] components = this.getComponents();
        if (components.length < 1) return;
        if (components[0].getBounds().width == 0) return;
        int width = Main.WIDTH - components[0].getBounds().width;

        System.out.println(components[0].getBounds().width + "");

        width /= 8;
        int height = width / 11 * 18;
        System.out.println(height + " " + width);

        for (DigitLabel label : labels) {
            label.setPreferredSize(new Dimension(width, height));
            label.setMaximumSize(new Dimension(width, height));
            label.setMinimumSize(new Dimension(width, height));
        }
        this.revalidate();
        this.repaint();
    }
}
