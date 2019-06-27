package panelUtils;




import com.company.factory.ServiceFactory;
import com.company.entity.PersonalInform;
import com.company.entity.Staff;
import com.company.service.PersonalInformService;
import com.company.service.StaffService;
import com.company.utils.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 个人消息的发送
 */
public class InsetPIPanel extends JPanel implements ActionListener,ItemListener{
    private JPanel topPanel;
    private JButton confirmButton;
    private JButton dateButton;
    private JTextField dateField;
    private JComboBox comboBox;
    private JComboBox nameBox;
    private JTextArea textArea;
    private String dateString;
    private Staff staff;
    private List<Staff> staffList;
    private PersonalInformService informService = ServiceFactory.getPersonalInformServiceInstance();
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();


    public InsetPIPanel(Staff staff) {
        this.staff = staff;
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        staffList = staffService.getAll(staff.getStaffNumber());
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        dateField = new JTextField();
        textArea = new JTextArea();
        Font font = new Font("微软雅黑",Font.BOLD,60);
        textArea.setFont(font);
        confirmButton = new JButton();
        dateButton = new JButton();
        confirmButton.addActionListener(this);
        dateButton.addActionListener(this);
        dateButton.setText("发送时间");
        confirmButton.setText("发送");
        textArea.setText("请输入私信内容");
        Style.setBigButtonStyle(confirmButton);
        Style.setBigButtonStyle(dateButton);
        Style.setFieldStyle(dateField);
        String[] strings = new String[staffList.size()];
        String[] nameStrings = new String[staffList.size()];
        for (int i=0;i<staffList.size();i++){
            String string = staffList.get(i).getStaffNumber();
            String name = staffList.get(i).getStaffName();
            if (string != staff.getStaffNumber()){
                strings[i] = new String(string);
                nameStrings[i] = new String(name);
            }
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel(strings);
        DefaultComboBoxModel<String> nameModel = new DefaultComboBoxModel<>(nameStrings);
        comboBox = new JComboBox(model);
        nameBox = new JComboBox(nameModel);
        comboBox.addItemListener(this);
        Style.setFieldStyle(comboBox);
        Style.setFieldStyle(nameBox);
        topPanel.add(comboBox);
        topPanel.add(nameBox);
        topPanel.add(dateButton);
        topPanel.add(dateField);
        topPanel.add(confirmButton);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(textArea,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dateButton){
            JTextField txtDate = new JTextField();
            //弹出时间选择框
            new DialogDatePicker(true, txtDate, 550, 250);
            //获得日期
            dateString = txtDate.getText();
            dateField.setText(dateString);
        }
        if (e.getSource() == confirmButton){
            String content = null;//内容
            String reiveID = comboBox.getSelectedItem().toString();//接收者工号
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                content = textArea.getText();
                date = sdf.parse(dateString);
                java.sql.Date sentTime = new java.sql.Date(date.getTime());
                PersonalInform personalInform = new PersonalInform(reiveID,staff.getStaffNumber(),content,sentTime);
                informService.addOneInform(personalInform);
                JOptionPane.showMessageDialog(null,"发送成功");
                textArea.setText("");
                dateField.setText("");
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null,"请选择时间");
            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(null,"请选择时间");
            }

        }
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        String staffNumber = e.getItem().toString();
        String name = staffService.getOndStaff(staffNumber).getStaffName();
        nameBox.setSelectedItem(name);
    }

    public static void main(String[] args) throws SQLException {


        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }


        StaffService staffService = ServiceFactory.getStaffServiceInstance();
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        Staff staff = staffService.getOndStaff("20010");
        frame.add(new InsetPIPanel(staff));
        frame.setVisible(true);
    }


}