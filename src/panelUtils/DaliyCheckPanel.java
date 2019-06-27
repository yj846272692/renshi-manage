package panelUtils;




import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import com.company.entity.Staff;
import com.company.service.CheckService;
import com.company.service.StaffService;
import com.company.utils.DialogDatePicker;
import com.company.utils.MyFailDialog;
import com.company.utils.MySuccessDialog;
import com.company.utils.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


import static java.awt.BorderLayout.CENTER;


/**
 * 每日考勤面板
 */
public class DaliyCheckPanel extends JPanel implements ActionListener {
    private JScrollPane scrollPane;
    private TableNew table;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private java.util.List<Staff> daliyChecks;
    private Iterator<Staff> iterator = null;
    private JPanel datePanel;
    private JPanel checkPanel;
    private JComboBox jComboBox;
    private int row;
    private JButton dateButton;
    private JLabel dateLabel;
    private JButton checkButton;
    private JTextField txtDate;
    private String dateString;
    private String[] content;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private String typeString;
    private String numberString;
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();

    public DaliyCheckPanel(){
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        init();
    }
    private void init(){
        scrollPane = new JScrollPane();
        table = new TableNew();
        dtm = new DefaultTableModel();
        txtDate = new JTextField();
        jComboBox = new JComboBox();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        dateLabel = new JLabel();
        content = new String[3];
        checkButton = new JButton("确认考勤");
        Style.setSmallButtonSytle(checkButton);
        checkButton.addActionListener(this);
        datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        checkPanel= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dateButton = new JButton("选择日期");
        Style.setSmallButtonSytle(dateButton);
        dateButton.addActionListener(this);
        daliyCheckPanel();
        datePanel.add(dateButton);
        datePanel.add(dateLabel);
        checkPanel.add(checkButton);
        this.add(scrollPane,CENTER);
        this.add(datePanel,BorderLayout.NORTH);
        this.add(checkPanel,BorderLayout.SOUTH);
        //table监听
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                String[] jb ={"全勤","缺勤","迟到","早退","请假"};
                MyComboBoxEditor abc1 = new MyComboBoxEditor(jb);
                MyComboBoxEditor abc2 = new MyComboBoxEditor(jb);
                table.setComboCell(row,2,abc1);
            }
        });
        //对下拉框监听
        jComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    typeString = String.valueOf(jComboBox.getSelectedIndex());
                }
            }
        });
    }

    //日常考勤面板
    private void daliyCheckPanel() {
        dtm = new DefaultTableModel();
        String[] titles ={"工号","姓名","状态"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        try{
            daliyChecks = staffService.getAll();
            iterator = daliyChecks.iterator();
            while (iterator.hasNext()){
                Staff staff = iterator.next();
                content[0] = staff.getStaffNumber();
                content[1] = staff.getStaffName();
                dtm.addRow(content);
            }
            for(int i =0;i<table.getRowCount();i++)
            {
                table.setValueAt("全勤",i,2);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane.setViewportView(table);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dateButton){
            new DialogDatePicker(true, txtDate, 500, 100);
            //获得日期
            dateString = txtDate.getText();
            dateLabel.setText(dateString);
        }
        if (e.getSource() == checkButton){
            iterator = daliyChecks.iterator();
            int row = 0;
            int n =0;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
                java.sql.Date checkDate = new java.sql.Date(date.getTime());
                while (iterator.hasNext()){
                    Staff staff = iterator.next();
                    content[0] = staff.getStaffNumber();
                    String NumberString = content[0];
                    String typeString = (String) table.getValueAt(row,2);
                    row++;
                    Check check = new Check(NumberString,typeString,checkDate);
                    n=checkService.addOneCheck(check);
                }
                if (n != 0) {
                    new MySuccessDialog("今日考勤成功！！");
                    txtDate.setText("");
                } else {
                    new MyFailDialog("今日考勤失败！！");
                }
                row = 0;
            } catch (ParseException e1) {
                e1.printStackTrace();
            }catch (NullPointerException e1){
                new MyFailDialog("请选择时间");
            }
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.add(new DaliyCheckPanel());
        frame.setVisible(true);
    }
}
