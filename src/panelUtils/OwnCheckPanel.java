package panelUtils;





import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import com.company.entity.Staff;
import com.company.service.CheckService;
import com.company.utils.Style;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;



/**
 * 个人查看考勤信息
 */
public class OwnCheckPanel extends JPanel implements ActionListener {
    private JScrollPane scrollPane;
    private JPanel buttonPanel;
    private JButton refreshButton;
    private JTable table;
    private DefaultTableModel dtm;
    private DefaultTableCellRenderer renderer,renderer1;
    private java.util.List<Check> ownChecks;
    private Staff staff;
    private Iterator<Check> iterator = null;
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();

    public OwnCheckPanel(Staff staff){
        super();
        this.staff = staff;
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());
        init();
    }

    private void init(){
        table = new JTable();
        scrollPane = new JScrollPane();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(187,187,187));
        refreshButton = new JButton("刷新");
        Style.setBigButtonStyle(refreshButton);
        refreshButton.addActionListener(this);
        buttonPanel.add(refreshButton);
        ownCheckTable();
        this.add(scrollPane,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    private void ownCheckTable() {
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
            ownChecks = checkService.getAllCheckBySN(staff.getStaffNumber());
            iterator = ownChecks.iterator();
            while (iterator.hasNext()){
                Check check = iterator.next();
                content[0] = staff.getStaffNumber();
                content[1] = staff.getStaffName();
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
        if (e.getSource()==refreshButton){
            ownCheckTable();
            table.revalidate();
            scrollPane.revalidate();
        }
    }
}
