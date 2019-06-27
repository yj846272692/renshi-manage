package panelUtils;

import com.company.chart.BarChart1;
import com.company.dao.StaffDAO;
import com.company.factory.DAOFactory;
import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.CheckService;
import com.company.service.DepartmentService;
import com.company.service.StaffService;
import com.company.utils.DateTime3;
import com.company.utils.RounderPanel;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class PersonlInfoPanel2 extends JPanel implements ActionListener {
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel linkPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JPanel bLeftPanel;
    private JPanel bRightPanel;
    private Staff staff;
    private JButton lookButton;
    private JButton modifyButton;
    private JButton checkButton;
    private JLabel[] iconLabels;
    private QueryPersonalInfo qpi;
    private ModifyPersonInfo mpi;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();

    public PersonlInfoPanel2(Staff staff) {
        this.staff = staff;
        init();
        this.setBackground(Color.WHITE);
        this.setVisible(true);
    }

    public void init() {
        panel1.add(getLeftPanel(), BorderLayout.WEST);
        panel1.add(getLinkPanel(),BorderLayout.EAST);
        panel3.add(getRightCardPanel());
        panel2.add(getbLeftPanel());
//        panel4.add(getbRightPanel());


        add(mainPanel);

    }

    JPanel getLinkPanel() {
        linkPanel = new JPanel();
        linkPanel.setPreferredSize(new Dimension(330,300));
        linkPanel.setBackground(Color.WHITE);
        linkPanel.setLayout(new GridLayout(4, 0, 5, 5));
//        iconLabels = new JLabel[5];
//        for (int i = 0; i < iconLabels.length; i++) {
//            iconLabels[i] = new JLabel();
//            iconLabels[i].setHorizontalAlignment(JLabel.CENTER);
//            linkPanel.add(iconLabels[i]);
//        }
//        iconLabels[0].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/微博.png")));
//        iconLabels[0].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    Runtime.getRuntime().exec("cmd /c start https://weibo.com/");
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//        iconLabels[1].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/百度(选中).png")));
//        iconLabels[1].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    Runtime.getRuntime().exec("cmd /c start https://www.baidu.com/");
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//        iconLabels[2].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/淘宝.png")));
//        iconLabels[2].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    Runtime.getRuntime().exec("cmd /c start https://www.taobao.com/");
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
//        iconLabels[3].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/爱奇艺.png")));
//        iconLabels[3].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                try {
//                    Runtime.getRuntime().exec("cmd /c start http://www.iqiyi.com/");
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
        return linkPanel;
    }

    RounderPanel getLeftPanel() {

        PersonalCardPanel pcp = new PersonalCardPanel(staff);
        pcp.setPreferredSize(new Dimension(375, 475));
//
        return pcp;
    }

    RounderPanel getRightCardPanel() {
        AllInformPanel allInformPanel = new AllInformPanel();
        allInformPanel.setPreferredSize(new Dimension(800,475));
        return allInformPanel;
    }

    JPanel getbLeftPanel() {
        DateTime3 dateTime3 = new DateTime3();
        return dateTime3;
    }

    RounderPanel getbRightPanel() {
        RounderPanel rounderPanel = new RounderPanel(Color.WHITE, 50);
        ChartPanel chartPanel = new BarChart1(staff).getChartPanel();
        chartPanel.setPreferredSize(new Dimension(500, 300));
        rounderPanel.setPreferredSize(new Dimension(800, 325));
        rounderPanel.add(chartPanel);
        return rounderPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lookButton) {
            qpi = new QueryPersonalInfo(staff.getStaffNumber());
            JFrame frame = new JFrame("个人信息窗体");
            System.out.println();
            frame.getContentPane().add(qpi);
            frame.setLocationRelativeTo(null);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
        if (e.getSource() == modifyButton) {
            mpi = new ModifyPersonInfo(staff);
            JFrame frame = new JFrame("修改个人信息");
            System.out.println();
            frame.getContentPane().add(mpi);
            frame.setLocationRelativeTo(null);
            frame.setSize(700, 700);
            frame.setVisible(true);
        }
//        if (e.getSource() == checkButton) {
//            JFrame frame = new JFrame("根据考勤类型统计员工考勤天数柱形图");
//            // 添加柱形图
//            frame.add(new BarChart1(staff).getChartPanel(), BorderLayout.CENTER);
//            frame.setSize(750, 500);
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        }
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
        frame.add(new PersonlInfoPanel2(staff));
        frame.setVisible(true);

    }
}
