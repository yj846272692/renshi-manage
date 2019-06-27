package com.company.utils;

import panelUtils.Memo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.*;


public class DateTime3 extends JPanel {
    private JPanel topPanel;
    private JPanel centerPanel;
    private Color bgColor;
    private Color cellColor;
    private JPanel showPanel;
    private RounderPanel rpPanel;
    private JPanel[] panels;
    private JLabel[] dateLabels;
    private JLabel[] infoLabels;
    private Integer j;

    public DateTime3() {
        cellColor = new Color(230, 230, 241);
        bgColor = Color.WHITE;
        this.setLayout(new BorderLayout());
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        topPanel = new JPanel();
        showCenterPanel();
        add(centerPanel);
    }

    public void showCenterPanel() {
        centerPanel.setBackground(bgColor);
        centerPanel.setPreferredSize(new Dimension(700, 350));
        rpPanel = new RounderPanel(bgColor, 40);
        rpPanel.setPreferredSize(new Dimension(700, 325));
        centerPanel.add(rpPanel);
        showPanel = new JPanel();
        showPanel.setBackground(bgColor);
        showPanel.setPreferredSize(new Dimension(675, 300));
        showPanel.setLayout(new GridLayout(0, 7, 5, 3));
        rpPanel.add(showPanel);
        String[] strings = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        JLabel[] labels = new JLabel[7];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(strings[i]);
            labels[i].setBackground(Color.lightGray);
            labels[i].setOpaque(true);
            labels[i].setHorizontalAlignment(JLabel.CENTER);
            labels[i].setFont(new Font("宋体", Font.BOLD, 20));
            showPanel.add(labels[i]);
        }
        //判断需要加多少个panel;
        Calendar cal = Calendar.getInstance();
        //本月第一天星期几
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //获得本月天数
        int monthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //总共要创的JPanel的个数
        int n = monthDay + weekday - 1;
        panels = new JPanel[n];
        dateLabels = new JLabel[n];
        infoLabels = new JLabel[n];
        for (int i = 0; i < n; i++) {
            panels[i] = new JPanel();
            panels[i].setBackground(bgColor);
            dateLabels[i] = new JLabel();
            dateLabels[i].setHorizontalAlignment(JLabel.CENTER);
            dateLabels[i].setFont(new Font("宋体", Font.PLAIN, 20));
            infoLabels[i] = new JLabel();
            infoLabels[i].setHorizontalAlignment(JLabel.CENTER);

            showPanel.add(panels[i]);
        }
        for (int i = weekday - 1; i < n; i++) {
            panels[i].setBackground(cellColor);
            panels[i].setLayout(new GridLayout(2, 0));
            panels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new Memo();
                    infoLabels[j].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/标记.png")));
                }
            });
            dateLabels[i].setText(String.valueOf((i + 2 - weekday)));
            dateLabels[i].setHorizontalAlignment(JLabel.CENTER);
            panels[i].add(dateLabels[i]);
            panels[i].add(infoLabels[i]);
        }

        for (int i = 0; i < n; i++) {
            String date = String.valueOf(LocalDate.now().getDayOfMonth());
            System.out.println(date);
            if (date.equals(dateLabels[i].getText().trim())) {
                infoLabels[i].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/现在定位.png")));
            }
        }


    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new DateTime3());
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
