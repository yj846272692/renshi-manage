package panelUtils;




import com.company.factory.ServiceFactory;
import com.company.entity.PersonalInform;
import com.company.entity.Staff;
import com.company.service.PersonalInformService;
import com.company.service.StaffService;
import com.company.utils.MyFailDialog;
import com.company.utils.MySuccessDialog;
import com.company.utils.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

/**
 * 个人通知的查看、刷新
 */
public class PersonalInformPanel extends JPanel implements ActionListener {
    private Staff staff;
    private JPanel buttonPanel1;
    private JScrollPane scrollPane1, scrollPane2;
    private JTable table1, table2;
    private DefaultTableModel dtm, dtm2;
    private JButton viewButton1,viewButton2,freshButton;
    private JButton deleteButton;
    private int count1;
    private int count2;
    private String[] content;
    private List<PersonalInform> personalInforms;
    private PersonalInformService personalInformService = ServiceFactory.getPersonalInformServiceInstance();
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();


    public PersonalInformPanel(Staff staff) {
        this.staff = staff;
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        init();
    }

    private void init() {
        buttonPanel1 = new JPanel();
        content = new String[6];
        scrollPane1 = new JScrollPane();
        scrollPane2 = new JScrollPane();
        viewButton1 = new JButton("查看已读");
        viewButton2 = new JButton("查看未读");
        deleteButton = new JButton("删除");
        freshButton = new JButton("刷新");
        Style.setBigButtonStyle(viewButton1);
        Style.setBigButtonStyle(viewButton2);
        Style.setBigButtonStyle(deleteButton);
        Style.setBigButtonStyle(freshButton);
        viewButton1.addActionListener(this);
        viewButton2.addActionListener(this);
        deleteButton.addActionListener(this);
        freshButton.addActionListener(this);
        table1 = new JTable();
        table2 = new JTable();
        scrollPane1.setPreferredSize(new Dimension(800,250));
        scrollPane2.setPreferredSize(new Dimension(800,250));
        showSeenTable();
        showInitialTable();
        buttonPanel1.add(viewButton1);
        buttonPanel1.add(viewButton2);
        buttonPanel1.add(deleteButton);
        buttonPanel1.add(freshButton);
        this.add(scrollPane1, BorderLayout.NORTH);
        this.add(scrollPane2,BorderLayout.CENTER);
        this.add(buttonPanel1, BorderLayout.SOUTH);
    }

    private void showInitialTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"序号", "接收者工号","发送人姓名", "内容", "日期", "状态"};
        dtm.setColumnIdentifiers(titles);
        table1.setModel(dtm);
        table1.setFont(new Font("微软雅黑",Font.BOLD,18));
        table1.setRowHeight(30);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table1.setDefaultRenderer(Object.class, r);
        // 将表头居中
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table1.getTableHeader().setDefaultRenderer(r1);
        try{
            personalInforms = personalInformService.getAllInformBySN(staff.getStaffNumber());
            Iterator<PersonalInform> iterator = personalInforms.iterator();
            while (iterator.hasNext()) {
                PersonalInform personalInform = iterator.next();
                String flag = personalInform.getIsRead();
                if ("yes".equals(flag)) {
                    content[0] = personalInform.getId().toString();
                    content[1] = personalInform.getReceiverNumber();
                    content[2] = staffService.getOndStaff(personalInform.getSenderNumber()).getStaffName();
                    content[3] = personalInform.getInformContent();
                    content[4] = personalInform.getSendDate().toString();
                    content[5] = personalInform.getIsRead();
                    dtm.addRow(content);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane1.setViewportView(table1);
        table1.revalidate();
        scrollPane1.revalidate();

    }

    private void showSeenTable() {
        dtm2 = new DefaultTableModel();
        String[] titles = {"序号", "接收者工号","发送人姓名", "内容", "日期", "状态"};
        dtm2.setColumnIdentifiers(titles);
        table2.setModel(dtm2);
        table2.setFont(new Font("微软雅黑",Font.BOLD,18));
        table2.setRowHeight(30);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table2.setDefaultRenderer(Object.class, r);
        // 将表头居中
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table2.getTableHeader().setDefaultRenderer(r1);
        try{
            personalInforms = personalInformService.getAllInformBySN(staff.getStaffNumber());
            Iterator<PersonalInform> iterator = personalInforms.iterator();
            while (iterator.hasNext()) {
                PersonalInform personalInform = iterator.next();
                String flag = personalInform.getIsRead();
                if ("no".equals(flag)) {
                    content[0] = personalInform.getId().toString();
                    content[1] = personalInform.getReceiverNumber();
                    content[2] = staffService.getOndStaff(personalInform.getSenderNumber()).getStaffName();
                    content[3] = personalInform.getInformContent();
                    content[4] = personalInform.getSendDate().toString();
                    content[5] = personalInform.getIsRead();
                    dtm2.addRow(content);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        scrollPane2.setViewportView(table2);
        table2.revalidate();
        scrollPane2.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewButton1){
             count1 = table1.getSelectedRow();
            String getContent = null;
            try{
                getContent = table1.getValueAt(count1, 3).toString();
                //创建新的窗口
                JFrame frame = new JFrame("新窗口");
                JTextArea textArea = new JTextArea(getContent);
                textArea.setBackground(new Color(223, 235, 232));
                textArea.setEditable(false);
                frame.getContentPane().add(textArea);
                frame.setLocationRelativeTo(null);
                frame.setSize(500,500);
                frame.setVisible(true);
            }catch (ArrayIndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null,"未选择中");
            }
        }
        if (e.getSource() == viewButton2){
            count2 = table2.getSelectedRow();
            String getContent1 = null;
            try{
                getContent1 = table2.getValueAt(count2, 3).toString();
                //创建新的窗口
                JFrame frame = new JFrame("消息");
                JTextArea textArea = new JTextArea(getContent1);
                textArea.setBackground(new Color(144, 160, 150));
                textArea.setEditable(false);
                frame.getContentPane().add(textArea);
                frame.setLocationRelativeTo(null);
                frame.setSize(500,500);
                frame.setVisible(true);
                int id = Integer.parseInt(table2.getValueAt(count2,0).toString());
                String receiverNumber = table2.getValueAt(count2,1).toString();
                PersonalInform inform = personalInformService.getOneInform(id,receiverNumber);
                inform.setIsRead("YES");
                personalInformService.updateInform(inform);
                dtm2.removeRow(count2);
            }catch (ArrayIndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(null,"未选择中");
            }
        }

        if (e.getSource() == deleteButton){
            count1 = table1.getSelectedRow();
            count2 = table2.getSelectedRow();
            String receiverNumber;
            boolean isSelected = false;
            int id;
            if (table1.isRowSelected(count1)){
                id = Integer.parseInt(table1.getValueAt(count1,0).toString());
                receiverNumber = table1.getValueAt(count1,1).toString();
                personalInformService.deleteOneInform(id,receiverNumber);
                dtm.removeRow(count1);
                isSelected = true;
                JOptionPane.showMessageDialog(null,"删除成功");
            }
            if (table2.isRowSelected(count2)){
                id = Integer.parseInt(table2.getValueAt(count2,0).toString());
                receiverNumber = table2.getValueAt(count2,1).toString();
                personalInformService.deleteOneInform(id,receiverNumber);
                dtm2.removeRow(count2);
                isSelected = true;
                System.out.println();
                JOptionPane.showMessageDialog(null,"删除成功");

            }
            if (isSelected == false){
                JOptionPane.showMessageDialog(null,"未选择中");

            }
        }
        if (e.getSource() == freshButton){
            showInitialTable();
            showSeenTable();
            this.revalidate();
        }
    }
}