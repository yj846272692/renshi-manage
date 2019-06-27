package com.company.utils;

import javax.swing.*;
import java.awt.*;


public class MyTextField extends JTextField {

    public MyTextField(String s){
        super(s);
    }
    public MyTextField(){
        super();
    }
    @Override
    protected void paintBorder(Graphics g) {
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 23, 23);
    }

}