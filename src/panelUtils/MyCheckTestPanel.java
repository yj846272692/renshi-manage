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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 考勤面板
 */
public class MyCheckTestPanel extends JPanel implements ActionListener{
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JButton dateButton;
    private JButton checkButton;
    private String dateString;
    private JLabel dateLabel;
    private JTextField txtDate;
    private List<Staff> list;
    private MyCheckPanel[] panels;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private CheckService checkService = ServiceFactory.getCheckServiceInstance();


    public MyCheckTestPanel(){
        this.setLayout(new BorderLayout());
        init();
    }

    public void init(){
        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,41,20));
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Font font = new Font("宋体",Font.BOLD,25);
        txtDate = new JTextField();
        txtDate.setFont(font);
        dateButton = new JButton("选择时间");
        checkButton = new JButton("确认考勤");
        Style.setBigButtonStyle(dateButton);
        Style.setBigButtonStyle(checkButton);
        dateButton.addActionListener(this);
        checkButton.addActionListener(this);
        dateLabel = new JLabel();
        dateLabel.setFont(font);

        topPanel.add(dateButton);
        topPanel.add(dateLabel);
        bottomPanel.add(checkButton);

        this.add(topPanel,BorderLayout.NORTH);
        this.add(getCenterPanel(),BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
    }

    public JPanel getCenterPanel(){
        list = staffService.getAll();
        panels = new MyCheckPanel[list.size()];
        for (int i=0;i<panels.length;i++){
            panels[i] = new MyCheckPanel(list.get(i));
            centerPanel.add(panels[i]);
        }
        return centerPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==dateButton){
            new DialogDatePicker(true, txtDate, 500, 100);
            //获得日期
            Font font = new Font("宋体",Font.BOLD,20);
            dateString = txtDate.getText();
            txtDate.setFont(font);
            dateLabel.setText(dateString);
        }
        if (e.getSource() == checkButton){
            List<Check> checkList = checkService.getAllCheck();
            Iterator<Check> iterator = checkList.iterator();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            boolean flag = false;
            while (iterator.hasNext()){
                Check check = iterator.next();
                Date date = new Date(check.getCheckDate().getTime());
                String timeString = sdf.format(date);
                Date todayDate = new Date();
                String todayTime = sdf.format(todayDate);
                if (timeString.equals(todayTime)){
                    flag = true;
                }
            }
            if (flag == false){
                int n =0;
                Date date = null;
                try {
                    date = sdf.parse(dateString);
                    java.sql.Date checkDate = new java.sql.Date(date.getTime());
                    for (int i =0;i<panels.length;i++){
                        Map<String,String> map = new HashMap<>();
                        map = panels[i].getMap();
                        String staffNumber = map.get("staffNumber").toString();
                        String type = map.get("type").toString();
                        Check check = new Check(staffNumber,type,checkDate);
                        n=checkService.addOneCheck(check);
                    }
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }catch (NullPointerException e1){
                    JOptionPane.showMessageDialog(null,"请选择时间");

                }
                if (n!=0){
                    JOptionPane.showMessageDialog(null,"考勤成功");
                    for (int i=0;i<panels.length;i++){
                        panels[i].moren();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"考勤失败");


                }
            }else {
                JOptionPane.showMessageDialog(null,"今日考勤已完成");

            }

        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new MyCheckTestPanel());
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
