package panelUtils;



import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.StaffService;


import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

/**
 * 修改个人基本信息，
 */
public class ModifyPersonInfo extends JPanel implements ActionListener {

    private Staff staff;
    private JTextField[] textFields;
    private JButton confirmButton;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    public ModifyPersonInfo(Staff staff){
        this.staff = staff;
        this.setBackground(new Color(223, 235, 232));
        this.setLayout(new GridLayout(3,1));
        init();
    }

    public void init(){
        confirmButton = new JButton("保存");
        confirmButton.addActionListener(this);
        Style.setBigButtonStyle(confirmButton);
        JPanel[] panels = new JPanel[3];
        for (int i=0;i<panels.length;i++){
            panels[i] = new JPanel();
            panels[i].setBackground(new Color(223, 235, 232));
        }
        JLabel[] labels = new JLabel[2];
        textFields = new JTextField[2];
        for (int i=0;i<labels.length;i++){
            labels[i] = new JLabel();
            textFields[i] = new JTextField();
            Style.setLabelStyle(labels[i]);
            Style.setFieldStyle(textFields[i]);
            labels[i].setOpaque(false);
            textFields[i].setOpaque(false);
            textFields[i].setEditable(true);
            textFields[i].setHorizontalAlignment(CENTER);
        }
        labels[0].setText("联系方式");
        labels[1].setText("邮箱");
        panels[0].add(labels[0]);
        panels[0].add(textFields[0]);
        panels[1].add(labels[1]);
        panels[1].add(textFields[1]);
        panels[2].add(confirmButton);

        for (int i=0;i<3;i++){
            this.add(panels[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton){
            if (textFields[0].getText().equals(null)||textFields[0].getText().equals("")||
                    textFields[1].getText().equals(null)||textFields[1].getText().equals("")){
                JOptionPane.showMessageDialog(null,"请输入完整信息");
            }else {
                String phone = textFields[0].getText();
                String mailbox = textFields[1].getText();
                staff.setStaffPhone(phone);
                staff.setStaffMailbox(mailbox);
                staffService.updateOneStaff(staff);
                JOptionPane.showMessageDialog(null,"修改成功");
                for (int i=0;i<2;i++){
                    textFields[i].setText("");
                }
            }
        }
    }
}