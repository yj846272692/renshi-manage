package com.company.utils;

import javax.swing.*;
import java.awt.*;

/**
 * 按钮的封装
 */
public class backgroundButton extends JButton {
    private int width;
    private int height;
    private String icon;
    private String words;
    private Color fontColor;
    private int fontSize;
    private String fontString;

    public backgroundButton(int width, int height, String icon, String words, Color fontColor, int fontSize, String fontString) {
        this.width = width;
        this.height = height;
        this.icon = icon;
        this.words = words;
        this.fontColor = fontColor;
        this.fontSize =fontSize;
        this.fontString = fontString;
        this.setText(this.words);
        this.setPreferredSize(new Dimension(this.width, this.height));

        this.setForeground(this.fontColor);
        this.setFont(new Font(fontString,Font.PLAIN,fontSize));
        this.setHorizontalTextPosition(CENTER);//设置标签的文本相对其图像的水平位置。
        this.setVerticalTextPosition(CENTER);//设置jButton上图标的位置
        this.setContentAreaFilled(false);//将button的背景设置为透明。
    }

    public backgroundButton() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/img/" + icon));
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.width, this.height, Image.SCALE_FAST));  //将图片按照当前大小自适应
        imageIcon.paintIcon(this, g, 0, 0);//将图片以填充方式绘制到整个面板上
        String string = new String(words);
        g.drawString(string, 30, 20);
        // Font font = new Font(fontString,Font.PLAIN,fontSize);
        // g.setFont(font);
        g.dispose();
    }
}

