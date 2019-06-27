package com.company.frame;

import com.company.dao.LoginDAO;
import com.company.entity.Staff;
import com.company.factory.DAOFactory;
import com.company.factory.ServiceFactory;
import com.company.service.LoginService;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**

 * 登录界面的制作
 */
public class LoginFrame extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JLabel closeLabel;
    private JLabel maxLabel;
    private JLabel minLabel;
    private JPanel centerPanel;
    private JPanel topPanel;
    private JPanel westPanel;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JButton loginButton;
    private JTextField textField;
    private JPasswordField passwordField;
    private LoginService loginService = ServiceFactory.getLoginServiceInstance();
    private LoginDAO loginDAO = DAOFactory.getLogin();



    public LoginFrame(){
        setTitle("登录界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/1.jpg"));
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(LoginFrame.this.getWidth(), LoginFrame.this.getHeight(), Image.SCALE_FAST));
                imageIcon.paintIcon(this, g, 0, 0);
            }
        };

//        centerPanel = new JPanel() {
//            @Override
//            protected void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/13.jpg"));
//                imageIcon.setImage(imageIcon.getImage().getScaledInstance(centerPanel.getWidth(), centerPanel.getHeight(), Image.SCALE_FAST));
//                imageIcon.paintIcon(this, g, 0, 0);
//            }
//        };
        centerPanel.setLayout(null);
        loginButton = new JButton("登录");
        loginButton.addActionListener(this);
        Font font = new Font("宋体",Font.BOLD,22);
        textField = new JTextField("请输入账号");
        textField.setFont(font);
        passwordField = new JPasswordField("20000");
        loginButton.setPreferredSize(new Dimension(200,40));
        textField.setPreferredSize(new Dimension(200,40));
        passwordField.setPreferredSize(new Dimension(200,40));

        centerPanel.add(textField);
        centerPanel.add(passwordField);
        centerPanel.add(loginButton);
        textField.setBounds(300,280,400,40);
        passwordField.setBounds(300,380,400,40);
        loginButton.setBounds(300,480,400,50);
        centerPanel.setOpaque(false);
        westPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout(380,210));
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(westPanel,BorderLayout.WEST);
        mainPanel.add(eastPanel,BorderLayout.EAST);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "是否退出", "标题", 0);
                if (result == 0) {
                    LoginFrame.this.dispose();
                }
            }
        });
        minLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFrame.this.setExtendedState(JFrame.ICONIFIED);

            }
        });
        maxLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFrame.this.setExtendedState(JFrame.MAXIMIZED_BOTH);

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //从数据库登录
        if (e.getSource() == loginButton) {
            String account = textField.getText();
            String password = new String(passwordField.getPassword());
            Map<String, Object> map = loginService.login(account, password);
            String info = map.get("info").toString();
            if ("登录成功".equals(info)) {
                JOptionPane.showMessageDialog(null, "登陆成功");
                new CompanyFrame((Staff) map.get("staff"), (java.util.List<String>) map.get("groupNames"), (Map<String, java.util.List<String>>) map.get("itemMap"));
                LoginFrame.this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "登录失败，请重新登录");
            }
        }


        //从文本文档登陆
//        if (e.getSource() == loginButton)
//        {
//            String account = textField.getText();
//            String password = passwordField.getText();
//            LoginDAO loginDAO1 = DAOFactory.getLogin();
//            Map map = new HashMap();
//            try
//            {
//                map = loginDAO1.getLogin(" ");
//            }
//            catch (IOException e1)
//            {
//                e1.printStackTrace();
//            }
//            if(map.get(account).equals(password))
//            {
//                JOptionPane.showMessageDialog(null,"登陆成功");
//                Map<String, Object> map1 = loginService.login(account,password);
////                System.out.println(map1);
////                System.out.println((Staff)map1.get("staff"));
////                System.out.println((java.util.List<String>) map1.get("groupNames"));
////                System.out.println((Map<String , java.util.List<String>>)map1.get("itemMap"));
//               new CompanyFrame((Staff)map1.get("staff"),(java.util.List<String>) map1.get("groupNames"),(Map<String , java.util.List<String>>)map1.get("itemMap"));
//
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(null,"登录失败，请重新登录");
//            }
//        }
//    }
    }








    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LoginFrame();
    }


}