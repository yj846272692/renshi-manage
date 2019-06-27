package panelUtils;

import com.company.factory.ServiceFactory;
import com.company.entity.*;
import com.company.service.*;
import com.company.utils.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**

 *         增加一个员工的面板封装
 */
public class AddOneStaff extends JPanel implements ActionListener, ItemListener {
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton saveButton;
    private JButton selectTimeButton;
    private JTextField timeField;
    private byte[] bytes;
    private ImageLabel imageLabel;
    private JLabel[] labels;
    private JTextField[] textFields;
    private String[] strings = {"工号", "姓名", "年龄", "性别", "部门", "职位", "籍贯", "婚况", "电话", "邮箱"};
    private String dateString;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private String sexString;
    private JComboBox<String> deptComboBox;
    private String deptName;
    private String deptNumber;
    private JComboBox<String> postComboBox;
    private String postName;
    private JComboBox<String> placeComboBox;
    private String placeName;
    private JComboBox<String> marriageBox;
    private String marriageName;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private LoginService loginService = ServiceFactory.getLoginServiceInstance();
    private LimitMapService limitMapService = ServiceFactory.getLimitMapServiceInstance();
    private LimitItemService limitItemService = ServiceFactory.getLimitItemServiceInstance();
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();
    private PersonalInformService personalInformService = ServiceFactory.getPersonalInformServiceInstance();
    private SalaryService salaryService = ServiceFactory.getSalaryServiceInstance();
    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();


    public AddOneStaff() {
        init();
        this.setLayout(new GridLayout(1, 2));
        this.add(getLeftPanel());
        this.add(getRightPanel());
    }

    public void init() {
        maleButton = new JRadioButton("男性");
        femaleButton = new JRadioButton("女性");
        maleButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        femaleButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
        maleButton.addItemListener(this);
        femaleButton.addItemListener(this);
        femaleButton.setOpaque(false);
        maleButton.setOpaque(false);
        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);
        deptComboBox = new JComboBox<>();
        postComboBox = new JComboBox<>();
        marriageBox = new JComboBox<>();
        placeComboBox = new JComboBox<>();
        deptComboBox.setBounds(0,0,200,40);
        postComboBox.setBounds(0,0,200,40);
        postComboBox.setBounds(0,0,200,40);
        marriageBox.setBounds(0,0,200,40);

        placeComboBox.addItemListener(this);
        marriageBox.addItemListener(this);
        postComboBox.addItemListener(this);
        deptComboBox.addItemListener(this);
        java.util.List<Department> deptList = departmentService.getAll();
        String[] deptNames = new String[deptList.size()];
        for (int i = 0; i < deptNames.length; i++) {
            deptNames[i] = new String(deptList.get(i).getDeptName());
        }
        DefaultComboBoxModel<String> deptNameModel = new DefaultComboBoxModel<>(deptNames);
        deptComboBox.setModel(deptNameModel);
        deptComboBox.setSelectedItem("财务部");

        String[] postNames = {"人事部主管", "财务部主管", "普通员工"};
        DefaultComboBoxModel<String> postModel = new DefaultComboBoxModel<>(postNames);
        postComboBox.setModel(postModel);
        postComboBox.setSelectedItem("普通员工");

        String[] hunType = {"已婚", "未婚"};
        DefaultComboBoxModel<String> hunModel = new DefaultComboBoxModel<>(hunType);
        marriageBox.setModel(hunModel);
        marriageBox.setSelectedItem("未婚");

        String[] placeNames = {"江苏", "北京", "上海", "浙江"};
        DefaultComboBoxModel<String> placeModel = new DefaultComboBoxModel<>(placeNames);
        placeComboBox.setModel(placeModel);
        placeComboBox.setSelectedItem("江苏");


        labels = new JLabel[strings.length];
        textFields = new JTextField[5];
        leftPanel = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new GridLayout(7, 1));
        saveButton = new JButton("保存");
        saveButton.setPreferredSize(new Dimension(400,40));
        saveButton.addActionListener(this);
        selectTimeButton = new JButton("选择时间");
        selectTimeButton.setPreferredSize(new Dimension(200,40));
        selectTimeButton.addActionListener(this);
        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(120, 30));
        timeField.setOpaque(false);
    }

    public JPanel getLeftPanel() {
        File file = new File("D:\\16.jpg");
        bytes = FileUtils.fileToBytes(file);
        imageLabel = new ImageLabel(200, 200, bytes);
        imageLabel.setOpaque(false);
        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        JPanel[] panels = new JPanel[4];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panels[i].setBackground(Color.WHITE);
        }
        panels[1].add(imageLabel);
        panels[3].add(selectTimeButton);
        panels[3].add(timeField);
        for (int j = 0; j < panels.length; j++) {
            centerPanel.add(panels[j]);
        }


        leftPanel.add(centerPanel);
        return leftPanel;
    }

    public JPanel getRightPanel() {
        JPanel[] panels = new JPanel[7];
        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panels[i].setBackground(Color.WHITE);
        }
        for (int j = 0; j < strings.length; j++) {
            labels[j] = new JLabel(strings[j]);
            Style.setLabelStyle(labels[j]);
            labels[j].setOpaque(false);
        }
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new JTextField();
            Style.setFieldStyle(textFields[i]);
            textFields[i].setOpaque(false);
        }
        panels[0].add(labels[0]);
        panels[0].add(textFields[0]);
        panels[0].add(labels[1]);
        panels[0].add(textFields[1]);


        panels[1].add(labels[2]);
        panels[1].add(textFields[2]);

        panels[2].add(labels[3]);
        panels[2].add(maleButton);
        panels[2].add(femaleButton);

        panels[3].add(labels[4]);
        panels[3].add(deptComboBox);
        panels[3].add(labels[5]);
        panels[3].add(postComboBox);

        panels[4].add(labels[6]);
        panels[4].add(placeComboBox);
        panels[4].add(labels[7]);
        panels[4].add(marriageBox);

        panels[5].add(labels[8]);
        panels[5].add(textFields[3]);
        panels[5].add(labels[9]);
        panels[5].add(textFields[4]);

        panels[6].add(saveButton);

        for (int i = 0; i < panels.length; i++) {
            rightPanel.add(panels[i]);
        }
        return rightPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectTimeButton) {
            JTextField textDate = new JTextField();
            new DialogDatePicker(true, textDate, 750, 450);
            dateString = textDate.getText();
            timeField.setText(dateString);
            String regEx = "[^0-9]";
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(dateString);
            String staffString = matcher.replaceAll("").trim();
            textFields[0].setText(staffString);
        }
        if (e.getSource() == saveButton) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
                //----
                java.sql.Date enterTime = new java.sql.Date(date.getTime());
                if (timeField.getText().equals(null) || timeField.getText().equals("") ||
                        textFields[0].getText().equals(null) || textFields[0].getText().equals("") ||
                        textFields[1].getText().equals(null) || textFields[1].getText().equals("") ||
                        textFields[2].getText().equals(null) || textFields[2].getText().equals("") ||
                        textFields[3].getText().equals(null) || textFields[3].getText().equals("") ||
                        textFields[4].getText().equals(null) || textFields[4].getText().equals("")) {
//
                    JOptionPane.showMessageDialog(null,"请输入完整信息");
                } else {
                    Staff staff = new Staff(
                            textFields[0].getText(),
                            textFields[1].getText(),
                            textFields[2].getText(),
                            sexString,
                            deptNumber,
                            postName,
                            placeName,
                            marriageName,
                            textFields[3].getText(),
                            textFields[4].getText(),
                            bytes, enterTime);
                    Login login = new Login(textFields[0].getText(), textFields[0].getText());
                    staffService.addOneStaff(staff);
                    loginService.addOneLogin(login);
                    //一下是新增员工时默认增加的权限项（增加一个发送个人私信，查看个人私信，查看个人考勤，查询个人工资）,
                    // 并默认为新员工增加一个个人私信，个人考勤，个人工资记录
                    String staffNumber = staff.getStaffNumber();
                    String[] itemNames = {"发送个人私信", "查询个人私信", "查询个人考勤", "查询个人工资"};
                    for (int i = 0; i < itemNames.length; i++) {
                        String itemID = limitItemService.getLimitItemID(itemNames[i]);
                        LimitMap limitMap = new LimitMap(staffNumber, itemID);
                        limitMapService.addOneLimitMap(limitMap);
                    }
                    Check check = new Check(staffNumber, "全勤", enterTime);
                    checkService.addOneCheck(check);
                    PersonalInform inform = new PersonalInform(staffNumber, "20010", "恭喜成为新的员工", enterTime);
                    personalInformService.addOneInform(inform);
                    Salary salary = new Salary(staffNumber, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, enterTime);
                    salaryService.addOneSalary(salary);
                    JOptionPane.showMessageDialog(null,"新增员工成功");

//
                    for (int i = 0; i < textFields.length; i++) {
                        textFields[i].setText("");
                    }
                    timeField.setText("");
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null,"请选择时间");

            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == postComboBox) {
            postName = e.getItem().toString();
        }
        if (e.getSource() == deptComboBox) {
            deptName = e.getItem().toString();
            deptNumber = departmentService.getOneDeptByName(deptName).getDeptNumber().toString();
        }
        if (e.getSource() == marriageBox) {
            marriageName = e.getItem().toString();
        }
        if (e.getSource() == placeComboBox) {
            placeName = e.getItem().toString();
        }
        if (e.getSource() == maleButton) {
            if (maleButton.isSelected()) {
                sexString = maleButton.getText();
            }
        }
        if (e.getSource() == femaleButton) {
            if (femaleButton.isSelected()) {
                sexString = femaleButton.getText();
            }
        }
    }


    public static void main(String[] args) {


        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame();
        frame.getContentPane().add(new AddOneStaff());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}