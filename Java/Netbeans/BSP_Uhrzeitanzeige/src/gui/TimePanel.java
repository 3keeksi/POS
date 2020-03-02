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
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
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
    private ClockLabel clock;
    TimePanel panel = this;

    public TimePanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (int i = 0; i < labels.length; i++) {
            DigitLabel label = new DigitLabel();
            labels[i] = label;
            this.add(label);
        }
//        this.clock = new ClockLabel(null);
//        this.add(clock);
    }

    public void init(ZoneId zone) {
        this.zone = zone;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            char[] chars;
            if (zone == null) {
                chars = new char[8];
                Arrays.fill(chars, '0');
//                clock.setTime(null);
            } else {
                time = LocalTime.now(zone);
                chars = DTF.format(time).toCharArray();
//                clock.setTime(time);
//                System.out.println("nibba");
            }
//            clock.repaint();
            for (int i = 0; i < labels.length; i++) {
                labels[i].setValue(chars[i]);
                labels[i].repaint();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void resize(int width) {
        Component[] components = this.getComponents();
        if (components.length < 1) {
            return;
        }
        int clockPx = 0;
        
        int w = width;

        w /= 8;
        int height = w / 11 * 18;

        for (DigitLabel label : labels) {
            label.setPreferredSize(new Dimension(w, height));
            label.setMaximumSize(new Dimension(w, height));
            label.setMinimumSize(new Dimension(w, height));
        }

        if (clock != null) {
            clock.setPreferredSize(new Dimension(clockPx, clockPx));
            clock.setMaximumSize(new Dimension(clockPx, clockPx));
            clock.setMinimumSize(new Dimension(clockPx, clockPx));
        }
        this.revalidate();
        this.repaint();
    }
}
