package panelUtils;



import com.company.entity.Salary;
import com.company.factory.ServiceFactory;
import com.company.entity.CheckStatistics;
import com.company.entity.Salary;
import com.company.service.CheckStatisticsService;
import com.company.service.SalaryService;
import com.company.utils.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**

 * 工资核算面板，及发放工资
 */
public class AccountSalaryPanel extends JPanel implements ActionListener{
    private JPanel topPanel;
    private JTextField timeField;
    private JTextField txtDate;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JButton selectTimeButton;
    private JButton releaseSalaryButton;
    private JTable table;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private List<CheckStatistics> statisticsList;
    private Iterator<CheckStatistics> iterator;
    private String dateString;
    private CheckStatisticsService service = ServiceFactory.getCheckSSInstance();
    private SalaryService salaryService = ServiceFactory.getSalaryServiceInstance();


    public AccountSalaryPanel(){
        this.setLayout(new BorderLayout());
        this.setBackground(Color.LIGHT_GRAY);
        init();
    }
    public void init(){
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectTimeButton = new JButton("选择发布时间");
        selectTimeButton.addActionListener(this);
        Style.setBigButtonStyle(selectTimeButton);
        timeField = new MyTextField();
        Style.setFieldStyle(timeField);
        txtDate = new JTextField();

        table = new JTable();
        scrollPane = new JScrollPane();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        showSalaryTable();

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        releaseSalaryButton = new JButton("发放工资");
        releaseSalaryButton.addActionListener(this);
        Style.setBigButtonStyle(releaseSalaryButton);

        topPanel.add(selectTimeButton);
        topPanel.add(timeField);
        buttonPanel.add(releaseSalaryButton);

        this.add(topPanel,BorderLayout.NORTH);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    public void showSalaryTable(){
        dtm = new DefaultTableModel();
        String[] titles = {"工号","姓名","职称","基础工资","补发工资","应扣工资","个人所得税",
                "社会保险","公积金","最终工资"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        table.setFont(new Font("微软雅黑",Font.BOLD,18));
        table.setRowHeight(30);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        String[] content = new String[10];
        try{
            statisticsList = service.getAllCheckSta();
            iterator = statisticsList.iterator();
            while (iterator.hasNext()){
                CheckStatistics statistics = iterator.next();
                Map<String,Object> map = AccountSalary.getSalaryInform(statistics);
                content[0] = map.get("staffNumber").toString();
                content[1] = map.get("name").toString();
                content[2] = map.get("postName").toString();
                content[3] = map.get("basicSalary").toString();
                content[4] = map.get("bfSalary").toString();
                content[5] = map.get("deductSalary").toString();
                content[6] = map.get("personTax").toString();
                content[7] = map.get("socialSec").toString();
                content[8] = map.get("reservedFunds").toString();
                content[9] = map.get("finalSalary").toString();
                dtm.addRow(content);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane.setViewportView(table);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectTimeButton){
            new DialogDatePicker(true, txtDate, 500, 100);
            //获得日期
            dateString = txtDate.getText();
            timeField.setText(dateString);
        }
        if (e.getSource() == releaseSalaryButton){
            int count = table.getRowCount();
            int n = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
                java.sql.Date checkDate = new java.sql.Date(date.getTime());
                for (int i=0;i<count;i++){
                    String staffNumber = table.getValueAt(i,0).toString();
                    double basicSalary = Double.valueOf(table.getValueAt(i,3).toString());
                    double bfSalary =  Double.valueOf(table.getValueAt(i,4).toString());
                    double deductSalary =  Double.valueOf(table.getValueAt(i,5).toString());
                    double personTax =  Double.valueOf(table.getValueAt(i,6).toString());
                    double socialSec =  Double.valueOf(table.getValueAt(i,7).toString());
                    double reservedFunds =  Double.valueOf(table.getValueAt(i,8).toString());
                    double finalSalary =  Double.valueOf(table.getValueAt(i,9).toString());
                    Salary salary = new Salary(staffNumber,basicSalary,bfSalary,deductSalary,personTax,socialSec,reservedFunds,finalSalary,new java.sql.Date(date.getTime()));
                    n = salaryService.addOneSalary(salary);
                }
                if (n != 0){
                    JOptionPane.showMessageDialog(null,"工资发放成功");
                }else {
                    JOptionPane.showMessageDialog(null,"工资发放失败");
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(null,"未选择时间");

            }
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");

        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.add(new AccountSalaryPanel());
        frame.setVisible(true);
    }
}