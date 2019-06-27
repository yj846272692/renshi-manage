package com.company.utils;

import javax.swing.*;
import java.awt.*;


public class MyPasswordField extends JPasswordField {
    public MyPasswordField(String s){
        super(s);
    }
//    @Override
//    protected void paintBorder(Graphics g) {
//        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 23, 23);
//    }
}