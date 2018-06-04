package com.test.practice.clock;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DigitalClock extends JFrame {
    WatchPanel watch = new WatchPanel();

    public DigitalClock() {
        super("Digital Clock");
        setSize(355, 70);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(1, 1, 15, 15));
        pane.add(watch);
        setContentPane(pane);
        show();
    }

    public static void main(String[] arguments) {
        DigitalClock clock = new DigitalClock();
    }
}

class WatchPanel extends JPanel implements Runnable {
    Thread runner;

    WatchPanel() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }
    }

    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D)comp;
        Font type = new Font("Serif", Font.BOLD, 24);
        comp2D.setFont(type);
        comp2D.setColor(getBackground());
        comp2D.fillRect(0, 0, getSize().width, getSize().height);
        GregorianCalendar day = new GregorianCalendar();
        String time = day.getTime().toString();
        comp2D.setColor(Color.black);
        comp2D.drawString(time, 5, 25);
    }
}