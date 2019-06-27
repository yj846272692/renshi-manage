package panelUtils;

import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.*;

import com.company.utils.MySuccessDialog;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 查看全体员工档案信息
 */
public class AllStaffPanel extends JPanel implements ActionListener{
    private JPanel buttonPanel;
    private JButton reflashButton;
    private JButton deleteButton;
    private JScrollPane scrollPane;
    private DefaultTableModel dtm;
    private JTable staffTable;
    private java.util.List<Staff> staffs;
    private int selectedRow;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private LoginService loginService = ServiceFactory.getLoginServiceInstance();
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();
    private CheckStatisticsService checkStatisticsService = ServiceFactory.getCheckSSInstance();
    private PersonalInformService personalInformService = ServiceFactory.getPersonalInformServiceInstance();
    private SalaryService salaryService = ServiceFactory.getSalaryServiceInstance();

    public AllStaffPanel() {
        this.setLayout(new BorderLayout());
        init();
    }

    public void init(){
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        reflashButton = new JButton("刷新");
        deleteButton = new JButton("删除员工");
        reflashButton.setPreferredSize(new Dimension(200,40));
        deleteButton.setPreferredSize(new Dimension(200,40));
        reflashButton.addActionListener(this);
        deleteButton.addActionListener(this);
        staffTable = new JTable();
        scrollPane = new JScrollPane();

        showAllStaff();
        buttonPanel.add(deleteButton);
        buttonPanel.add(reflashButton);
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    public void showAllStaff() {
        dtm = new DefaultTableModel();
        String[] titles = {"工号", "姓名", "年龄", "性别", "职称", "籍贯", "婚姻状态", "手机号", "邮箱", "入职时间"};
        dtm.setColumnIdentifiers(titles);
        staffTable.setModel(dtm);
        staffTable.setFont(new Font("宋体",Font.BOLD,20));
        staffTable.setRowHeight(30);
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(JLabel.CENTER);
        staffTable.setDefaultRenderer(Object.class, dtcr);

        DefaultTableCellRenderer dtcr1 = new DefaultTableCellRenderer();
        dtcr1.setHorizontalAlignment(JLabel.CENTER);
        dtcr1.setBackground(Color.LIGHT_GRAY);
        staffTable.getTableHeader().setDefaultRenderer(dtcr1);

        String[] content = new String[10];
        try{
            staffs = staffService.getAll();
            Iterator<Staff> iterator = staffs.iterator();
            while (iterator.hasNext()) {
                Staff staff = iterator.next();
                content[0] = staff.getStaffNumber();
                content[1] = staff.getStaffName();
                content[2] = staff.getStaffAge();
                content[3] = staff.getStaffSex();
                content[4] = staff.getStaffPost();
                content[5] = staff.getStaffPlace();
                content[6] = staff.getStaffMarriage();
                content[7] = staff.getStaffPhone();
                content[8] = staff.getStaffMailbox();
                content[9] = staff.getStaffEntrytime().toString();
                dtm.addRow(content);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane.setViewportView(staffTable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reflashButton){
            showAllStaff();
            staffTable.revalidate();
            scrollPane.revalidate();
        }
        if (e.getSource() == deleteButton){
            selectedRow = staffTable.getSelectedRow();
            if (selectedRow<0){
                JOptionPane.showMessageDialog(null,"请选择员工");

            }else {
                String staffNumber = staffTable.getValueAt(selectedRow,0).toString();
                staffService.deleteOneStaff(staffNumber);
                loginService.deleteOneLogin(staffNumber);
                checkService.deleteCheckBySN(staffNumber);
                checkStatisticsService.deleteCheckSta(staffNumber);
                personalInformService.deleteInforms(staffNumber);
                salaryService.deleteSalaryBySN(staffNumber);
                dtm.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null,"删除成功");
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
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new AllStaffPanel());
        frame.setVisible(true);

    }


}
