package panelUtils;




import com.company.chart.PersonalSalaryBarChart;
import com.company.chart.SalaryPieChart;
import com.company.factory.ServiceFactory;
import com.company.entity.Salary;
import com.company.entity.Staff;
import com.company.service.SalaryService;
import com.company.service.StaffService;
import com.company.utils.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import java.util.List;



/**
 * 个人工资条
 */
public class OwnSalaryPanel extends JPanel implements ActionListener,ItemListener{
    private JPanel LeftPanel;
    private Staff staff;
    private JPanel onePanel;
    private JPanel secondPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton refleshButton;
    private JComboBox timeComboBox;
    private String timeString;
    private JButton secondButton;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private List<Salary> salaryList;
    private Iterator<Salary> iterator = null;
    private SalaryService salaryService = ServiceFactory.getSalaryServiceInstance();
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();

    public OwnSalaryPanel(Staff staff){
        this.staff = staff;
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridLayout(2,1));
        init();
    }
    private void init(){
        LeftPanel = new JPanel();
        onePanel = new JPanel(new BorderLayout());
        secondPanel = new JPanel(new GridLayout(1,2));
        table = new JTable();
        scrollPane = new JScrollPane();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        refleshButton = new JButton("刷新");
        secondButton = new JButton("每次工资详细");
        secondButton.addActionListener(this);
        refleshButton.addActionListener(this);
        Style.setBigButtonStyle(refleshButton);
        Style.setBigButtonStyle(secondButton);
        timeComboBox = new JComboBox();
        timeComboBox.addItemListener(this);
        Style.setFieldStyle(timeComboBox);
        this.add(getOnePanel());
        this.add(getSecondPanel());
    }

    public void initComboBox(){
        List<String> timeList = salaryService.getPersonalSalaryTime(staff.getStaffNumber());
        String[] times = new String[timeList.size()];
        for (int i=0;i<timeList.size();i++){
            times[i] = new String(timeList.get(i));
        }
        DefaultComboBoxModel timeModel = new DefaultComboBoxModel(times);
        timeComboBox.setModel(timeModel);
        timeString = timeComboBox.getSelectedItem().toString();
    }

    public JPanel getOnePanel(){
        ownSalaryTable();
        onePanel.add(scrollPane,BorderLayout.CENTER);
        return onePanel;
    }

    public JPanel getSecondPanel(){
        secondPanel.add(getLeftPanel());
        secondPanel.add(getRightPanel());
        return secondPanel;
    }

    public JPanel getLeftPanel(){
        initLeftPanel();
        return LeftPanel;
    }

    public JPanel getRightPanel(){
        initComboBox();
        JPanel rightPanel = new JPanel(new GridLayout(3,1));
        JPanel[] panels = new JPanel[3];
        for (int i=0;i<panels.length;i++){
            panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
        }
        panels[1].add(timeComboBox);
        panels[1].add(secondButton);
        panels[1].add(refleshButton);
        for (int i=0;i<panels.length;i++){
            rightPanel.add(panels[i]);
        }
        return rightPanel;
    }

    public void initLeftPanel(){
        LeftPanel.add(new PersonalSalaryBarChart(staff).getChartPanel());
    }

    private void ownSalaryTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"工号","姓名","职称","基础工资","补发工资","扣除工资","个人所得税","社保","公积金","最终所得工资","发放时间"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        table.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.setRowHeight(30);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        String[] content = new String[11];
        try{
            salaryList = salaryService.getAllpersonSalary(staff.getStaffNumber());
            iterator = salaryList.iterator();
            while (iterator.hasNext()){
                Salary salary = iterator.next();
                content[0] = salary.getStaffNumber();
                content[1] = staffService.getOndStaff(salary.getStaffNumber()).getStaffName();
                content[2] = staffService.getOndStaff(salary.getStaffNumber()).getStaffPost().toString();
                content[3] = salary.getBasicSalary().toString();
                content[4] = salary.getBfSalary().toString();
                content[5] = salary.getDeductSalary().toString();
                content[6] = salary.getPersonalTax().toString();
                content[7] = salary.getSocialSec().toString();
                content[8] = salary.getReservedFunds().toString();
                content[9] = salary.getFinalSalary().toString();
                content[10] = salary.getTime().toString();
                dtm.addRow(content);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane.setViewportView(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == secondButton){
            JFrame frame = new JFrame("根据工资类型统计的个人工资详情饼状图");
            // 添加柱形图
            frame.add(new SalaryPieChart(staff,timeString).getChartPanel(), BorderLayout.CENTER);
            frame.setSize(750, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        if (e.getSource() == refleshButton){
            ownSalaryTable();
            table.revalidate();
            scrollPane.revalidate();
            initComboBox();
            LeftPanel.removeAll();
            initLeftPanel();
            LeftPanel.revalidate();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        timeString = e.getItem().toString();
    }



    public static void main(String[] args) {
        Staff staff = ServiceFactory.getStaffServiceInstance().getOndStaff("20010");
        JFrame frame = new JFrame("测试");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.add(new OwnSalaryPanel(staff));
        frame.setVisible(true);
    }



}
