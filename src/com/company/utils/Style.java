package com.company.utils;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * 皮肤的封装
 */
public class Style {
    public final static Dimension BUTTON_SIZE = new Dimension(160, 50);
    public final static Dimension FIELD_SIZE = new Dimension(150, 50);

    public final static Color GREEN = new Color(51, 104, 51);
    public final static Color BLUE = new Color(95, 172, 216);
    public final static Color WHITE = new Color(255, 255, 255);
    public final static Color GREEN1 = new Color(92, 184, 92);
    public final static Color BLACK = new Color(50, 50, 50);


    public final static Font NORMAL_FONT = new Font("宋体", Font.PLAIN, 20);
    public final static Font BOLD_FONT = new Font("宋体", Font.BOLD, 24);
    public final static Font NOMAL_FONT = new Font("宋体", Font.PLAIN, 16);

    public static void setBigButtonStyle(JButton button) {
        button.setPreferredSize(BUTTON_SIZE);
        button.setBackground(Color.white);
        button.setForeground(Color.black);
        button.setFont(NORMAL_FONT);
    }

    public static void setSmallButtonSytle(JButton button){
        button.setPreferredSize(FIELD_SIZE);
        button.setBackground(BLUE);
        button.setForeground(WHITE);
        button.setFont(NORMAL_FONT);
    }

    public static void setFieldStyle(JComponent component) {
        component.setPreferredSize(FIELD_SIZE);
        component.setBackground(Color.white);
        component.setForeground(new Color(45, 45, 50));
        component.setFont(NORMAL_FONT);
        component.setBorder(new MatteBorder(0,0,2,0,Color.BLACK));
        component.setOpaque(false);
    }
    public static void setLoginFieldStyle(JComponent component) {
        component.setPreferredSize(FIELD_SIZE);
        component.setBackground(BLUE);
        component.setForeground(Color.WHITE);
        component.setFont(NORMAL_FONT);
        component.setBorder(new MatteBorder(0,0,2,0,Color.BLACK));
        component.setOpaque(false);
    }

    public static void setLabelStyle(JLabel label) {
        label.setForeground(BLACK);
        label.setFont(BOLD_FONT);
    }

    public static void setCheckBoxStyle(JCheckBox checkBox){
        checkBox.setFont(NORMAL_FONT);
    }
    public static void setLabelFont(JLabel label){
        label.setFont(NORMAL_FONT);
    }

}