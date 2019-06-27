package com.company.frame;


import com.company.entity.Staff;
import com.company.utils.Style;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import panelUtils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class CompanyFrame extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JPanel sysTopPanel;
    private JPanel sysNavPanel;
    private JButton logoutButton;
    private JLabel logoLabel;
    private JLabel homeLabel;
    private JPanel sysCardPanel;
    private JLabel nameLabel;
    private Staff staff ;
    private java.util.List<String> groupNames;
    private  Map<String,List<String>> itemMap;
    private CardLayout cardLayout;
    private JButton[] groupNameButtons = null;
    private AttencePanel ap;
    private EmployeePanel ep;
    private NoticePanel np;
    private SalaryMPanel sp;
    private PersonalInfoPanel pip;
    private PersonlInfoPanel2 pip2;
    private LimtiMPanel lmp;


    public CompanyFrame(Staff staff,List<String> groupNames,Map<String ,List<String>> itemMap)  {
        this.staff = staff;
        this.groupNames = groupNames;
        this.itemMap = itemMap;
        init();
        setTitle("人事管理系统");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(sysCardPanel,"pip2");
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyFrame.this.dispose();
                new LoginFrame();
            }
        });
    }
    public void init(){

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        nameLabel.setText(staff.getStaffName());
        sysCardPanel.setLayout(cardLayout);
        pip2 = new PersonlInfoPanel2(staff);
        sysCardPanel.add(pip2,"pip2");
        groupNameButtons = new JButton[groupNames.size()];
       for (int i=0;i<groupNames.size();i++){
           groupNameButtons[i] = new JButton(groupNames.get(i));
           Style.setBigButtonStyle(groupNameButtons[i]);
           groupNameButtons[i].addActionListener(this);
           if("员工管理".equals(groupNames.get(i))){
               ep = new EmployeePanel(itemMap.get(groupNames.get(i)),staff);
               sysCardPanel.add(ep,"panel" + i);
           }
           if ("考勤管理".equals(groupNames.get(i))){
               ap = new AttencePanel(itemMap.get(groupNames.get(i)),staff);
               sysCardPanel.add(ap,"panel" +i);
           }
           if ("工资管理".equals(groupNames.get(i))){
               sp = new SalaryMPanel(itemMap.get(groupNames.get(i)),staff);
               sysCardPanel.add(sp,"panel" + i);
           }
           if ("通知管理".equals(groupNames.get(i))){
               np = new NoticePanel(itemMap.get(groupNames.get(i)),staff);
               sysCardPanel.add(np,"panel" + i);
           }
           if ("权限管理".equals(groupNames.get(i))){
               lmp = new LimtiMPanel(itemMap.get(groupNames.get(i)),staff);
               sysCardPanel.add(lmp,"panel" + i);
           }
       }
        for (int i=0;i<groupNameButtons.length;i++){
            sysNavPanel.add(groupNameButtons[i]);
        }
        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0;i<itemMap.size();i++){
            if (e.getSource()==groupNameButtons[i]){
                cardLayout.show(sysCardPanel,"panel" + i);
            }
        }
    }


}