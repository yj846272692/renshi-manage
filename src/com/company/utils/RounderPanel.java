package com.company.utils;

import javax.swing.*;
import java.awt.*;

/**
 * 封装的圆角面板
 */
public class RounderPanel extends JPanel {
    private Color blackColor;
    private Integer arc;

    public RounderPanel(Color blackColor, Integer arc){
        super();
        this.blackColor = blackColor;
        this.arc = arc;
        this.revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(blackColor);
        g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, arc ,arc);

    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(51, 159,236));
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, arc ,arc);
        super.paintBorder(g);
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(400,300);
        f.getContentPane().setBackground(Color.WHITE);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setLocationRelativeTo(null);

        RounderPanel rounderPanel = new RounderPanel(Color.cyan,0);
        f.add(rounderPanel);
    }
}
