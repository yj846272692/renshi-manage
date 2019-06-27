package com.company.utils;

/**
 * 新增了系统时间的实时显示
 */

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;




public class DateTime2 extends JApplet {
    private static final long serialVersionUID = 1L;
    public static final String WEEK_SUN = "日";
    public static final String WEEK_MON = "一";
    public static final String WEEK_TUE = "二";
    public static final String WEEK_WED = "三";
    public static final String WEEK_THU = "四";
    public static final String WEEK_FRI = "五";
    public static final String WEEK_SAT = "六";

    public static final Color background = Color.white;
    public static final Color foreground = Color.black;
    public static final Color headerBackground = Color.CYAN;
    public static final Color headerForeground = Color.black;
    public static final Color selectedBackground = Color.CYAN;
    public static final Color selectedForeground = Color.gray;

    private JPanel cPane;
    private JTable daysTable;
    private AbstractTableModel daysModel;
    private Calendar calendar;
    private JPanel timePanel;
    private JLabel label;



    public DateTime2() {
        cPane = (JPanel) getContentPane();
    }

    public class ThreadTest implements Runnable { //实现Runnable接口
        @Override
        public void run() { //线程执行的方法
            while (true) {
                try {

                    Date now = new Date();

                    DateFormat da = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM); //显示日期，时间（精确到分）
                    String str = da.format(now);

                    label.setText(str);


                    Thread.sleep(1000);//休眠1毫秒
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }


    }
    @Override
    public void init() {
        cPane.setLayout(new BorderLayout());

        timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel();

        daysTable = new JTable();
        timePanel.add(label);

        calendar = Calendar.getInstance();

        cPane.add(timePanel,BorderLayout.NORTH);

        daysModel = new AbstractTableModel() {

            private static final long serialVersionUID = -3517455337953024330L;

            @Override
            public int getRowCount() {
                return 7;
            }

            @Override
            public int getColumnCount() {
                return 7;
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (row == 0) {
                    return getHeader(column);
                }
                row--;
                Calendar calendar = (Calendar) DateTime2.this.calendar.clone();
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                int dayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                int moreDayCount = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                int index = row * 7 + column;
                int dayIndex = index - moreDayCount + 1;
                if (index < moreDayCount || dayIndex > dayCount) {
                    return null;
                } else {
                    return new Integer(dayIndex);
                }
            }
        };

        daysTable = new CalendarTable(daysModel, calendar);
        daysTable.setCellSelectionEnabled(true);
        daysTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        daysTable.setDefaultRenderer(daysTable.getColumnClass(0), new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                String text = (value == null) ? "" : value.toString();
                JLabel cell = new JLabel(text);
                cell.setOpaque(true);
                if (row == 0) {
                    cell.setForeground(headerForeground);
                    cell.setBackground(headerBackground);
                } else {
                    if (isSelected) {
                        cell.setForeground(selectedForeground);
                        cell.setBackground(selectedBackground);
                    } else {
                        cell.setForeground(foreground);
                        cell.setBackground(background);
                    }
                }

                return cell;
            }
        });
        updateView();
        cPane.add(daysTable, BorderLayout.CENTER);
    }

    public static String getHeader(int index) {
        switch (index) {
            case 0:
                return WEEK_SUN;
            case 1:
                return WEEK_MON;
            case 2:
                return WEEK_TUE;
            case 3:
                return WEEK_WED;
            case 4:
                return WEEK_THU;
            case 5:
                return WEEK_FRI;
            case 6:
                return WEEK_SAT;
            default:
                return null;
        }
    }

    public void updateView() {
        daysModel.fireTableDataChanged();
        daysTable.setRowSelectionInterval(calendar.get(Calendar.WEEK_OF_MONTH),
                calendar.get(Calendar.WEEK_OF_MONTH));
        daysTable.setColumnSelectionInterval(calendar.get(Calendar.DAY_OF_WEEK) - 1,
                calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    public static class CalendarTable extends JTable {
        private static final long serialVersionUID = 1L;
        private Calendar calendar;

        public CalendarTable(TableModel model, Calendar calendar) {
            super(model);
            this.calendar = calendar;
        }

        @Override
        public void changeSelection(int row, int column, boolean toggle, boolean extend) {
            super.changeSelection(row, column, toggle, extend);
            if (row == 0) {
                return;
            }
            Object obj = getValueAt(row, column);
            if (obj != null) {
                calendar.set(Calendar.DAY_OF_MONTH, ((Integer) obj).intValue());
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DateTime2 myCalendar = new DateTime2();
        myCalendar.init();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().add(myCalendar);
        frame.setSize(500, 200);
        new Thread(myCalendar.new ThreadTest()).start();
    }
}

