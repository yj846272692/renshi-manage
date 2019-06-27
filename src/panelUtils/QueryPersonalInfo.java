package panelUtils;



import com.company.dao.StaffDAO;
import com.company.factory.DAOFactory;
import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.StaffService;
import com.company.utils.ImageLabel;
import com.company.utils.MyTextField;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static javax.swing.SwingConstants.CENTER;


/**
 * 查看个人档案信息
 */
public class QueryPersonalInfo extends JPanel implements ActionListener{
    private Staff staff;
    private String staffNumber;
//    private JButton reflashButton;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    public QueryPersonalInfo(String staffNumber){
        this.staffNumber = staffNumber;
        this.setBackground(new Color(216, 213, 241));
        this.setLayout(new GridLayout(7,1));
//        reflashButton = new JButton("刷新");
//        reflashButton.addActionListener(this);
//        Style.setBigButtonStyle(reflashButton);
        init();

    }


    public void init(){
        this.staff = staffService.getOndStaff(staffNumber);
        JPanel[] panels = new JPanel[7];
        for (int i=0;i<panels.length;i++){
            panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panels[i].setBackground(new Color(223, 235, 232));
        }
        JLabel[] labels = new JLabel[11];
        JTextField[] textFields = new JTextField[11];
        for (int i=0;i<labels.length;i++){
            labels[i] = new JLabel();
            textFields[i] = new JTextField();
            Style.setLabelStyle(labels[i]);
            Style.setFieldStyle(textFields[i]);
            labels[i].setOpaque(false);
            textFields[i].setOpaque(false);
            textFields[i].setEditable(false);
            textFields[i].setHorizontalAlignment(CENTER);
        }
        //第一行
        ImageLabel imageLabel = new ImageLabel(60,60,staff.getStaffPicture());
        panels[0].add(imageLabel);
        //第二行
        labels[0].setText("工号");
        textFields[0].setText(staff.getStaffNumber());
        labels[1].setText("姓名");
        textFields[1].setText(staff.getStaffName());
        panels[1].add(labels[0]);
        panels[1].add(textFields[0]);
        panels[1].add(labels[1]);
        panels[1].add(textFields[1]);
        //第三行
        labels[2].setText("年龄");
        textFields[2].setText(staff.getStaffAge());
        labels[3].setText("性别");
        textFields[3].setText(staff.getStaffSex());
        panels[2].add(labels[2]);
        panels[2].add(textFields[2]);
        panels[2].add(labels[3]);
        panels[2].add(textFields[3]);
        //第四行
        labels[4].setText("部门编号");
        textFields[4].setText(staff.getDeptNumber());
        labels[5].setText("职位");
        textFields[5].setText(staff.getStaffPost());
        panels[3].add(labels[4]);
        panels[3].add(textFields[4]);
        panels[3].add(labels[5]);
        panels[3].add(textFields[5]);
        //第五行
        labels[6].setText("籍贯");
        textFields[6].setText(staff.getStaffPlace());
        labels[7].setText("婚姻");
        textFields[7].setText(staff.getStaffMarriage());
        panels[4].add(labels[6]);
        panels[4].add(textFields[6]);
        panels[4].add(labels[7]);
        panels[4].add(textFields[7]);
        //第六行
        labels[8].setText("联系方式");
        textFields[8].setText(staff.getStaffPhone());
        labels[9].setText("邮箱");
        textFields[9].setText(staff.getStaffMailbox());
        panels[5].add(labels[8]);
        panels[5].add(textFields[8]);
        panels[5].add(labels[9]);
        panels[5].add(textFields[9]);
        //第七行
        labels[10].setText("入职时间");
        textFields[10].setText(staff.getStaffEntrytime().toString());
        panels[6].add(labels[10]);
        panels[6].add(textFields[10]);
//        panels[6].add(reflashButton);

        for (int i=0;i<panels.length;i++){
            this.add(panels[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == reflashButton){
//            init();
//        }
    }

    public static void main(String[] args) {
        StaffDAO staffDAO = DAOFactory.getStaffDAOInstance();
        Staff staff = null;
        try {
            staff = staffDAO.getOneStaff("20010");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new QueryPersonalInfo("20010"));
        frame.setVisible(true);
    }


}