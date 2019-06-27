package panelUtils;


import com.company.chart.BarChart1;
import com.company.dao.StaffDAO;
import com.company.factory.DAOFactory;
import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.CheckService;
import com.company.service.DepartmentService;
import com.company.service.StaffService;
import com.company.utils.DateTime;
import com.company.utils.RounderPanel;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 *         个人信息面板的封装，
 */
public class PersonalInfoPanel extends JPanel implements ActionListener {
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel bLeftPanel;
    private JPanel bRightPanel;
    private Staff staff;
    private JButton lookButton;
    private JButton modifyButton;
    private JButton checkButton;
    private QueryPersonalInfo qpi;
    private ModifyPersonInfo mpi;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();

    public PersonalInfoPanel(Staff staff) {
        this.staff = staff;

        init();
    }


    public void init() {
        //顶部面板内容的设置
        topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(getLeftPanel());
        topPanel.add(getRightCardPanel());

        //底部面板设置
        bottomPanel = new JPanel(new GridLayout(1, 2));
        bottomPanel.add(getbLeftPanel());
        bottomPanel.add(getbRightPanel());

        this.add(topPanel);
        this.add(bottomPanel);

    }


    RounderPanel getLeftPanel() {

        PersonalCardPanel pcp = new PersonalCardPanel(staff);
//        leftPanel = new JPanel();
//        leftPanel.setLayout(new GridLayout(3,1));
//        JPanel[] panels = new JPanel[3];
//        for (int i=0;i<panels.length;i++){
//            panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
//            panels[i].setBackground(new Color(254,233,190));
//        }
//        ImageLabel imageLabel = new ImageLabel(120,120,staff.getStaffPicture());
//        imageLabel.setOpaque(false);
//        JLabel dpLabel = new JLabel( departmentService.getOneDept(staff.getDeptNumber()).getDeptName() + "-" + staff.getStaffPost());
//        dpLabel.setOpaque(false);
//        Style.setLabelStyle(dpLabel);
//        lookButton = new JButton("查看个人信息");
//        modifyButton = new JButton("修改个人信息");
//        Style.setBigButtonStyle(modifyButton);
//        Style.setBigButtonStyle(lookButton);
//        lookButton.addActionListener(this);
//        modifyButton.addActionListener(this);
//        panels[0].add(imageLabel);
//        panels[1].add(dpLabel);
//        panels[2].add(lookButton);
//        panels[2].add(modifyButton);
//
//        for (int i=0;i<panels.length;i++){
//            leftPanel.add(panels[i]);
//        }
        return pcp;
    }

    RounderPanel getRightCardPanel() {
        AllInformPanel allInformPanel = new AllInformPanel();

        return allInformPanel;
    }

    RounderPanel getbLeftPanel() {
//        bLeftPanel = new JPanel();
//        bLeftPanel.setBackground(new Color(0, 255, 153));
//        DateTime myCalendar = new DateTime();
//        myCalendar.init();
//        bLeftPanel.add(myCalendar);
//        bLeftPanel.setVisible(true);
//        return bLeftPanel;
        RounderPanel rounderPanel = new RounderPanel(Color.WHITE, 50);
        DateTime myCalendar = new DateTime();
        myCalendar.init();
        rounderPanel.add(myCalendar);
        rounderPanel.setVisible(true);
        return rounderPanel;
    }

    RounderPanel getbRightPanel() {
//        bRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        bRightPanel.setBackground(new Color(153, 153, 51));
//        checkButton = new JButton("个人考勤统计");
//        Style.setBigButtonStyle(checkButton);
//        checkButton.addActionListener(this);
//        Map<String, Integer> map = null;
//        try {
//            map = checkService.makeOneCheckStatistics(staff.getStaffNumber());
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        if (map != null) {
//            bRightPanel.add(checkButton);
//        }
//        return bRightPanel;
        RounderPanel rounderPanel = new RounderPanel(Color.WHITE, 50);
        ChartPanel chartPanel = new BarChart1(staff).getChartPanel();
        rounderPanel.add(chartPanel);
        return rounderPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == lookButton) {
            qpi = new QueryPersonalInfo(staff.getStaffNumber());
            JFrame frame = new JFrame("个人信息窗体");
            frame.getContentPane().add(qpi);
            frame.setLocationRelativeTo(null);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
        if (e.getSource() == modifyButton) {
            mpi = new ModifyPersonInfo(staff);
            JFrame frame = new JFrame("修改个人信息");
            frame.getContentPane().add(mpi);
            frame.setLocationRelativeTo(null);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
        if (e.getSource() == checkButton) {
            JFrame frame = new JFrame("根据考勤类型统计员工考勤天数柱形图");
            // 添加柱形图
            frame.add(new BarChart1(staff).getChartPanel(), BorderLayout.CENTER);
            frame.setSize(750, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        StaffDAO staffDAO = DAOFactory.getStaffDAOInstance();
        Staff staff = null;
        try {
            staff = staffDAO.getOneStaff("20010");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new PersonalInfoPanel(staff));
        frame.setVisible(true);

    }

}