package panelUtils;

import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Memo extends JFrame{
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton safeButton;
    private JPanel bottomPanel;
    private JPanel topPanel;

    public Memo() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        textArea1 = new JTextArea();
        textArea1.setFont(new Font("微软雅黑",Font.PLAIN,20));
        Style.setBigButtonStyle(safeButton);
        setSize(400,300);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
        safeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Memo.this.dispose();
            }
        });
        mainPanel.add(topPanel,BorderLayout.NORTH);
        mainPanel.add(textArea1);
        mainPanel.add(bottomPanel,BorderLayout.SOUTH);
        add(mainPanel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Memo");
        frame.setContentPane(new Memo().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
