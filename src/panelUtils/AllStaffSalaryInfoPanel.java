package panelUtils;





import com.company.factory.ServiceFactory;
import com.company.entity.Salary;
import com.company.service.SalaryService;
import com.company.service.StaffService;
import com.company.utils.MySuccessDialog;
import com.company.utils.SalaryExportExcel;
import com.company.utils.Style;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * 全体员工工资详情面板封装
 */

public class AllStaffSalaryInfoPanel extends JPanel implements ActionListener{
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JButton reflashButton;
    private JTable table;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private java.util.List<Salary> salaries;
    private Iterator<Salary> iterator;
   private SalaryService salaryService = ServiceFactory.getSalaryServiceInstance();
   private StaffService staffService = ServiceFactory.getStaffServiceInstance();

    public AllStaffSalaryInfoPanel() {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        init();
    }
    private void init(){
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(187,187,187));

        reflashButton = new JButton("刷新");

        reflashButton.setPreferredSize(new Dimension(200,40));

        reflashButton.addActionListener(this);
        table = new JTable();
        scrollPane = new JScrollPane();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        salaryTable();

        buttonPanel.add(reflashButton);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    public void salaryTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"工号","姓名","职称","基础工资","补发工资","应扣工资","个人所得税",
                "社会保险","公积金","最终工资","时间"};
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
            salaries = salaryService.getAllSalary();
            iterator = salaries.iterator();
            while (iterator.hasNext()){
                Salary salary = iterator.next();
                System.out.println("");
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



    public static void main(String[] args) {

        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("测试");
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.add(new AllStaffSalaryInfoPanel());
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

