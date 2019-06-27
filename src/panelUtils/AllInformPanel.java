package panelUtils;


import com.company.factory.ServiceFactory;
import com.company.entity.AllInform;
import com.company.service.AllInformService;
import com.company.service.DepartmentService;
import com.company.service.StaffService;
import com.company.utils.RounderPanel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**

 *  查看全体通知的面板的封装
 */
public class AllInformPanel extends RounderPanel implements ActionListener {
    private JPanel buttonPanel;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel dtm;
    private JButton viewButton;
    private JButton flashButton;
    private java.util.List<AllInform> allInforms;
    private AllInformService allInformService = ServiceFactory.getAllInformServiceInstance();
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();
    private JPanel showPanel;

    public AllInformPanel() {
        super(Color.WHITE, 50);
        showPanel = new JPanel();
        showPanel.setLayout(new BorderLayout());
        showPanel.setOpaque(false);
        showPanel.setPreferredSize(new Dimension(775,425));
        init();
        add(showPanel);
    }

    public void init() {
        table = new JTable();
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        buttonPanel.setBackground(Color.WHITE);
        scrollPane = new JScrollPane();
        viewButton = new JButton("查看");
        flashButton = new JButton("刷新");
        flashButton.addActionListener(this);
        viewButton.addActionListener(this);

        showInformTable();
        buttonPanel.add(viewButton);
        buttonPanel.add(flashButton);
        showPanel.add(scrollPane, BorderLayout.CENTER);
        showPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showInformTable() {
        dtm = new DefaultTableModel();
        String[] titles = {"序号", "发送者部门", "标题", "内容", "时间"};
        dtm.setColumnIdentifiers(titles);
        table.setModel(dtm);
        table.setFont(new Font("微软雅黑", Font.BOLD, 20));
        table.setRowHeight(40);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        // 将表头居中
        DefaultTableCellRenderer r1 = new DefaultTableCellRenderer();
        r1.setHorizontalAlignment(JLabel.CENTER);
        r1.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(r1);
        //内容字符串数组
        String[] content = new String[5];
        try {
            allInforms = allInformService.getAllInform();
            Iterator<AllInform> iterator = allInforms.iterator();
            while (iterator.hasNext()) {
                AllInform allInform = iterator.next();
                content[0] = allInform.getId().toString();
                content[1] = departmentService.getOneDept(staffService.getOndStaff(allInform.getSenderNumber()).getDeptNumber()).getDeptName();
                content[2] = allInform.getInformTitle();
                content[3] = allInform.getInformContent();
                content[4] = allInform.getSendDate().toString();
                dtm.addRow(content);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        scrollPane.setViewportView(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == flashButton) {
            showInformTable();
            table.revalidate();
            scrollPane.revalidate();
        }
        if (e.getSource() == viewButton) {
            try {
                int count = table.getSelectedRow();
                System.out.println(count);
                String getContent = table.getValueAt(count, 3).toString();
                //创建新的窗口
                JFrame frame = new JFrame("新窗口");
                JTextArea textArea = new JTextArea(getContent);
                Font font = new Font("微软雅黑",Font.BOLD,30);
                textArea.setBackground(new Color(192, 192, 192));
                textArea.setFont(font);
                textArea.setEditable(false);
                frame.getContentPane().add(textArea);
                frame.setLocationRelativeTo(null);
                frame.setSize(500, 500);
                frame.setVisible(true);
            } catch (ArrayIndexOutOfBoundsException e1) {
                JOptionPane.showMessageDialog(null,"未选择");
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
        frame.add(new AllInformPanel());
        frame.setVisible(true);
    }


}