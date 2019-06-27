package com.company.utils;

import javax.swing.*;
import java.awt.*;

/**
 * 绘图面板，可以将指定宽度、高度、名称的图片自适应绘制到面板上
 *
 * @author moqi xu
 * @version 1.0
 */
public class ImageLabel extends JLabel {
    private int width;   //宽
    private int height;  //高
    private byte[] b;  //图片路径



    public ImageLabel(int width, int height, byte[] b) {
        this.width = width;
        this.height = height;
        this.b = b;
        this.setPreferredSize(new Dimension(this.width, this.height));  //设置当前面板合适大小
    }

    public ImageLabel() {
        super();   //调用父类JPanel的构造方法
    }

    /**
     * 重写父类JComponent的paintComponent(Graphics g)方法，用来对组件进行绘制
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//调用父类的成员方法
        ImageIcon imageIcon = new ImageIcon(b);//根据指定图片的路径生成图标对象
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(this.width, this.height, Image.SCALE_FAST));  //将图片按照当前面板大小自适应
        imageIcon.paintIcon(this, g, 0, 0);   //将图片绘制到整个面板上，以填充方式
    }
}
