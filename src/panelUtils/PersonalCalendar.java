package panelUtils;


import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import com.company.service.CheckService;
import com.company.utils.RounderPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;


public class PersonalCalendar extends JPanel {
    private JPanel topPanel;
    private JPanel centerPanel;
    private Color bgColor;
    private Color cellColor;
    private String staffNumber;
    private JPanel showPanel;
    private RounderPanel rpPanel;
    private JPanel[] panels;
    private List<Check> currentChecks;
    private Check check;
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();

    public PersonalCalendar(String staffNumber) {
        this.staffNumber = staffNumber;
        cellColor = new Color(255, 231, 147);
        bgColor = Color.WHITE;
        this.setLayout(new BorderLayout());
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        topPanel = new JPanel();
        currentChecks = new ArrayList<>();
        showCenterPanel();
        showTopPanel();
        add(centerPanel);
        add(topPanel, BorderLayout.NORTH);
    }

    public void showTopPanel() {
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setPreferredSize(new Dimension(1300, 40));
        JLabel[] labels = new JLabel[5];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setFont(new Font("宋体", Font.BOLD, 18));
            topPanel.add(labels[i]);
        }
        labels[0].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/刷新.png")));
        labels[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCenterPanel();
                updateUI();
                revalidate();
                centerPanel.revalidate();

            }
        });
        labels[1].setText(":全勤");
        labels[1].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/对.png")));
        labels[2].setText(":请假");
        labels[2].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/请假.png")));
        labels[3].setText(":迟到，早退");
        labels[3].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/警告.png")));
        labels[4].setText(":缺勤");
        labels[4].setIcon(new ImageIcon(ImageIcon.class.getResource("/img/错误.png")));
    }

    public void showCenterPanel() {
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(1500, 800));
        rpPanel = new RounderPanel(bgColor, 40);
        rpPanel.setPreferredSize(new Dimension(1500, 775));
        centerPanel.add(rpPanel);
        showPanel = new JPanel();
        showPanel.setBackground(bgColor);
        showPanel.setPreferredSize(new Dimension(1475, 750));
        showPanel.setLayout(new GridLayout(0, 7, 3, 3));
        rpPanel.add(showPanel);
        String[] strings = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        JLabel[] labels = new JLabel[7];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(strings[i]);
            labels[i].setBackground(Color.lightGray);
            labels[i].setOpaque(true);
            labels[i].setHorizontalAlignment(JLabel.CENTER);
            labels[i].setFont(new Font("宋体", Font.BOLD, 18));
            showPanel.add(labels[i]);
        }
        //判断需要加多少个panel;
        Calendar cal = Calendar.getInstance();
        //本月第一天星期几
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //获得本月天数
        int monthDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //总共要创的JPanel的个数
        int n = monthDay + weekday - 1;
        panels = new JPanel[n];
        JLabel[] dateLabels = new JLabel[n];
        JLabel[] infoLabels = new JLabel[n];
        for (int i = 0; i < n; i++) {
            panels[i] = new JPanel();
            panels[i].setBackground(bgColor);
            dateLabels[i] = new JLabel();
            dateLabels[i].setHorizontalAlignment(JLabel.CENTER);
            dateLabels[i].setFont(new Font("宋体", Font.PLAIN, 18));
            infoLabels[i] = new JLabel();
            infoLabels[i].setHorizontalAlignment(JLabel.CENTER);

            showPanel.add(panels[i]);
        }
        //获得个人考勤状况
        List<Check> list = new ArrayList<>();
        list = checkService.getAllCheckBySN(staffNumber);
//        list.forEach(check1 -> System.out.println(check1));
        Iterator<Check> iterator = list.iterator();
        while (iterator.hasNext()) {
            check = iterator.next();
            //判断考勤时间是否在本月
            Date date = new Date(check.getCheckDate().getTime());
//            System.out.println(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String yearMonth = sdf.format(date);
//            System.out.println(yearMonth);
            String currentYearMonth = sdf.format(new Date());
//            System.out.println(currentYearMonth);
            //获得本月的个人考勤
            if (currentYearMonth.equals(yearMonth)) {
                currentChecks.add(check);
            }
        }
//        currentChecks.forEach(check1 -> System.out.println(check1));

        for (int i = weekday - 1; i < n; i++) {
            panels[i].setBackground(cellColor);
            panels[i].setLayout(new GridLayout(2, 0));
            dateLabels[i].setText(String.valueOf((i + 2 - weekday)));
            //判断并添加考勤信息到日历中去
            for (Check check1 : currentChecks) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd");
                Date d = new Date(check1.getCheckDate().getTime());
                String date = sdf.format(d);
                String newDate = date.replaceAll("^(0+)", "");
                if (newDate.equals(dateLabels[i].getText().trim())) {
                    infoLabels[i].setText(check1.getCheckType());
                }
            }
            dateLabels[i].setHorizontalAlignment(JLabel.CENTER);
            panels[i].add(dateLabels[i]);
            panels[i].add(infoLabels[i]);
        }

        for (JLabel label : infoLabels) {
            label.setFont(new Font("宋体", Font.BOLD, 18));
            if ("缺勤".equals(label.getText().trim())) {
                label.setText("");
                label.setIcon(new ImageIcon(ImageIcon.class.getResource("/img/错误.png")));
            }
            if ("迟到".equals(label.getText().trim())) {
                label.setText("");
                label.setIcon(new ImageIcon(ImageIcon.class.getResource("/img/警告.png")));
            }
            if ("早退".equals(label.getText().trim())) {
                label.setText("");
                label.setIcon(new ImageIcon(ImageIcon.class.getResource("/img/警告.png")));
            }
            if ("请假".equals(label.getText().trim())) {
                label.setText("");
                label.setIcon(new ImageIcon(ImageIcon.class.getResource("/img/请假.png")));
            }
            if ("全勤".equals(label.getText().trim())) {
                label.setText("");
                label.setIcon(new ImageIcon(ImageIcon.class.getResource("/img/对.png")));
            }
        }

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new PersonalCalendar("20010"));
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
