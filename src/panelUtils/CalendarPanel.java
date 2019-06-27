package panelUtils;





import com.company.chart.BarChart1;
import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import com.company.entity.Staff;
import com.company.service.CheckService;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**

 * 个人查看考勤面板
 */
public class CalendarPanel extends JPanel{
    private Staff staff;
    private JPanel mainPanel;
    private JPanel jScrollPane;
    private JPanel jScrollPane2;
    private JPanel jScrollPane3;
    private java.util.List<Check> ownchecks;
    private Iterator<Check> iterator;
    private JTable checksTable12;
    private JTable checksTable1;
    private JTable table;
    private JPanel topPanel;
    private JButton previousButton;
    private JButton nextButton;
    private DefaultTableModel dtm;
    private DefaultTableModel dtm1;
    private DefaultTableModel dtm2;
    private DefaultTableCellRenderer renderer,renderer1;
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();

    public CalendarPanel(Staff staff){
        this.staff = staff;
        this.setLayout(new BorderLayout());
        topPanel = new JPanel(new BorderLayout());
        previousButton = new JButton("上一月");
        nextButton = new JButton("下一月");
        checksTable12 = new JTable();
        checksTable1 = new JTable();
        mainPanel = new JPanel();
        jScrollPane = new JPanel();
        jScrollPane2 = new JPanel();
        jScrollPane3 = new JPanel();
        dtm = new DefaultTableModel();
        table = new JTable();
        renderer = new DefaultTableCellRenderer();
        renderer1 = new DefaultTableCellRenderer();
        topPanel.add(previousButton,BorderLayout.WEST);
        topPanel.add(nextButton,BorderLayout.EAST);

        init();

    }

    private void init() {
        owncheckTable();
        checksTable12();
        checksTable1();
        add(topPanel,BorderLayout.NORTH);
        add(mainPanel,BorderLayout.CENTER);
        add(new BarChart1(staff).getChartPanel(),BorderLayout.SOUTH);
        mainPanel.setLayout(new CardLayout());
        JScrollPane A =new JScrollPane(checksTable1);
        JScrollPane B =new JScrollPane(checksTable12);
        mainPanel.add(A);
        mainPanel.add(B);
        A.setVisible(false);
        B.setVisible(true);
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                B.setVisible(true);
                A.setVisible(false);
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                A.setVisible(true);
                B.setVisible(false);
            }
        });
    }

    private void owncheckTable() {
        dtm1 = new DefaultTableModel();
        String[] titles = {"编号","工号","状态","日期"};
        dtm1.setColumnIdentifiers(titles);
        table.setModel(dtm1);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setDefaultRenderer(renderer1);
        String[] content = new String[4];
        try{
            ownchecks = checkService.getAllCheckBySN(staff.getStaffNumber());
            iterator = ownchecks.iterator();
            while (iterator.hasNext()){
                Check check = iterator.next();
                content[0] = String.valueOf(check.getId());
                content[1] = check.getStaffNumber();
                content[2] = check.getCheckType();
                content[3] = check.getCheckDate().toString();
                dtm1.addRow(content);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    private void checksTable12() {
        dtm = new DefaultTableModel();
        String[] titles = {"日","一","二","三","四","五","六"};
        String[][] day ={{"","","","","","01","02"},
                {"","","","","","无","无"},
                {"03","04","05","06","07","08","09"},
                {"无","无","无","无","无","无","无"},
                {"10","11","12","13","14","15","16"},
                {"无","无","无","无","无","无","无"},
                {"17","18","19","20","21","22","23"},
                {"无","无","无","无","无","无","无"},
                {"24","25","26","27","28","29","30"},
                {"无","无","无","无","无","无","无"},
                {"31","","","","","",""},
                {"无","","","","","",""}};

        dtm.setColumnIdentifiers(titles);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        checksTable12.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        checksTable12.getTableHeader().setDefaultRenderer(renderer1);
        checksTable12.setRowHeight(30);

        for(int i=0;i<table.getRowCount();i++)
        {
            String riqi =(String) table.getValueAt(i,3);
            String zhuangtai =(String) table.getValueAt(i,2);
            String a[] = riqi.split("-");
            if(a[1].equals("12")) {
                for (int l = 0; l < day.length; l++) {
                    for (int j = 0; j < day[l].length; j++) {
                        if (a[2].equals(day[l][j])) {
                            day[l + 1][j] = zhuangtai;
                        }
                    }
                }
            }
        }

        for(int i =0 ;i<12;i++) {
            dtm.addRow(day[i]);
        }
        checksTable12.setModel(dtm);
        Color[] color={Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan,
                Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan};
        setColor(checksTable12,color);
    }

    private void checksTable1() {
        dtm2 = new DefaultTableModel();
        String[] titles = {"日","一","二","三","四","五","六"};
        String[][] day ={{"","01","02","03","04","05","06"},
                {"","无","无","无","无","无","无"},
                {"07","08","09","10","11","12","13"},
                {"无","无","无","无","无","无","无"},
                {"14","15","16","17","18","19","20"},
                {"无","无","无","无","无","无","无"},
                {"21","22","23","24","25","26","27"},
                {"无","无","无","无","无","无","无"},
                {"28","29","30","31","","",""},
                {"无","无","无","无","","",""}};

        dtm2.setColumnIdentifiers(titles);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        checksTable1.setDefaultRenderer(Object.class,renderer);
        renderer1.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.LIGHT_GRAY);
        checksTable1.getTableHeader().setDefaultRenderer(renderer1);
        checksTable1.setRowHeight(35);

        for(int i=0;i<table.getRowCount();i++)
        {
            String riqi =(String) table.getValueAt(i,3);
            String zhuangtai =(String) table.getValueAt(i,2);
            String a[] = riqi.split("-");
            if(a[1].equals("01")) {
                for (int l = 0; l < day.length; l++) {
                    for (int j = 0; j < day[l].length; j++) {
                        if (a[2].equals(day[l][j])) {
                            day[l + 1][j] = zhuangtai;
                        }
                    }
                }
            }
        }

        for(int i =0 ;i<10;i++) {
            dtm2.addRow(day[i]);
        }
        checksTable1.setModel(dtm2);
        Color[] color={Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan,
                Color.LIGHT_GRAY,Color.cyan,Color.LIGHT_GRAY,Color.cyan};
        setColor(checksTable1,color);
    }


//    public static void main(String[] args) {
//        JFrame frame = new JFrame("测试");
//        frame.setSize(1200,600);
//        frame.setLocationRelativeTo(null);
//        frame.add(new CalendarPanel());
//        frame.setVisible(true);
//        frame.show();
//    }
    public static void setColor(JTable table,Color[] color) {
        try {
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) {
                    setBackground(color[row]);
                    setForeground(Color.BLACK);
                    return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
                }
            };
            int columnCount = table.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(dtcr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
