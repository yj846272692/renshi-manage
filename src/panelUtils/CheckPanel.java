package panelUtils;







import com.company.chart.CountBarChart;
import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import com.company.entity.CheckStatistics;
import com.company.service.CheckService;
import com.company.service.CheckStatisticsService;
import com.company.service.StaffService;
import com.company.utils.ExportExcel;
import com.company.utils.MySuccessDialog;
import com.company.utils.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


/**
 * 查看所有员工的考勤信息,导出到外部Excel，一键考勤，刷新
 */
public class CheckPanel extends JPanel implements ActionListener {
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private java.util.List<Check> checks;
    private JPanel buttonPanel;
    private JButton checkButton;
    private JButton refalshButton;
    private JButton exportButton;
    private JButton countButton;
    private Iterator<Check> iterator = null;
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private CheckStatisticsService statisticsService = ServiceFactory.getCheckSSInstance();

    public CheckPanel(){
        super();
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        init();
    }

    private void init(){
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(187,187,187));
        checkButton = new JButton("生成统计考勤表");
        refalshButton = new JButton("刷新");
        exportButton = new JButton("导出");
        countButton = new JButton("查看考勤统计");
        checkButton.addActionListener(this);
        refalshButton.addActionListener(this);
        exportButton.addActionListener(this);
        countButton.addActionListener(this);
        Style.setBigButtonStyle(checkButton);
        Style.setBigButtonStyle(refalshButton);
        Style.setBigButtonStyle(exportButton);
        Style.setBigButtonStyle(countButton);
        table = new JTable();
        scrollPane = new JScrollPane();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        checkTable();
        buttonPanel.add(exportButton);
        buttonPanel.add(countButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(refalshButton);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    private void checkTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"工号","姓名","状态","日期"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        table.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.setRowHeight(30);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        String[] content = new String[4];
        try{
            checks = checkService.getAllCheck();
            iterator = checks.iterator();
            while (iterator.hasNext()){
                Check check = iterator.next();
                String staffName = staffService.getOndStaff(check.getStaffNumber()).getStaffName();
                content[0] = check.getStaffNumber();
                content[1] = staffName;
                content[2] = check.getCheckType();
                content[3] = check.getCheckDate().toString();
                dtm.addRow(content);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane.setViewportView(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refalshButton){
            checkTable();
            table.revalidate();
            scrollPane.revalidate();
        }
        if (e.getSource() == exportButton){
            // 调用工具类，将list中数据写入指定路径的excel文件中
            ExportExcel.exportData(checks);
            JOptionPane.showMessageDialog(null,"导出成功");
        }
        if (e.getSource() == countButton){
            JFrame frame = new JFrame("考勤情况统计");
            frame.add(new CountBarChart().getChartPanel(),BorderLayout.CENTER);
            frame.setSize(750,500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        if (e.getSource() == checkButton){
            java.util.List<String> staffNumbers = checkService.getAllStaffNumber();
            Map<String,Map<String,Integer>> mapMap = checkService.makeAllCheckStatistics(staffNumbers);
            for (Map.Entry<String,Map<String,Integer>> entry : mapMap.entrySet()){
                String staffNumber = entry.getKey();
                Map<String,Integer> map = entry.getValue();
                CheckStatistics statistics = new CheckStatistics();
                for (Map.Entry<String,Integer> entry1 : map.entrySet()){
                    String type = entry1.getKey();
                    int count = entry1.getValue();
                    statistics.setStaffNumber(staffNumber);
                    if ("全勤".equals(type)){
                        statistics.setNormalCount(count);
                    }
                    if ("缺勤".equals(type)){
                        statistics.setTruantCount(count);
                    }
                    if ("迟到".equals(type)){
                        statistics.setLateCount(count);
                    }
                    if ("早退".equals(type)){
                        statistics.setLeaveCount(count);
                    }
                    if ("请假".equals(type)){
                        statistics.setHolidayCount(count);
                    }
                }
                statisticsService.addOneCheckSta(statistics);
            }
            JOptionPane.showMessageDialog(null,"一键考勤成功");

        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.add(new CheckPanel());
        frame.setVisible(true);
    }


}
