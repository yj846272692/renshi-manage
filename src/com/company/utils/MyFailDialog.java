package com.company.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自定义错误对话框
 */
public class MyFailDialog extends JDialog{
    private String info;
    private JLabel infoLabel;
    private JButton okButton;
    private JLabel iconLabel;

    public MyFailDialog(String info) {
        this.info = info;
        //设置为模态框
        setModal(true);
        setUndecorated(true);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,1));
        this.getContentPane().setBackground(Color.WHITE);
        iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(ImageIcon.class.getResource("/img/错误提示.png")));
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        add(iconLabel);
        infoLabel = new JLabel(info);
        Style.setLabelStyle(infoLabel);
        infoLabel.setBounds(100, 80, 200, 50);
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(infoLabel);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        okButton = new JButton("确定");
        Style.setBigButtonStyle(okButton);
        okButton.setForeground(Color.WHITE);
        okButton.setBounds(90, 200, 120, 50);
        okButton.setHorizontalAlignment(JButton.CENTER);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 MyFailDialog.this.dispose();
            }
        });
        panel.add(okButton);
        add(panel);
        setVisible(true);
    }
}
