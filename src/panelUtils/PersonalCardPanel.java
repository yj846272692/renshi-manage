package panelUtils;





import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.DepartmentService;
import com.company.utils.ImageLabel;
import com.company.utils.RounderPanel;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 个人卡片信息面板的封装
 */
public class PersonalCardPanel extends RounderPanel implements ActionListener {
    private JPanel showPanel;
    private Staff staff;
    private JButton checkButton;
    private JButton modifyButton;
    private QueryPersonalInfo qpi;
    private ModifyPersonInfo mpi;
    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();

    public PersonalCardPanel(Staff staff) {
        super(Color.WHITE,50);
        this.staff = staff;
        init();
    }

    public void init(){
        showPanel = new JPanel(new GridLayout(3,1));
        showPanel.setPreferredSize(new Dimension(350,450));
        checkButton = new JButton("查看个人信息");
        modifyButton = new JButton("修改个人信息");
        checkButton.addActionListener(this);
        modifyButton.addActionListener(this);
        Style.setBigButtonStyle(checkButton);
        Style.setBigButtonStyle(modifyButton);
        JPanel[] panels = new JPanel[3];
        for (int i=0;i<panels.length;i++){
            panels[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panels[i].setBackground(new Color(254,233,190));
        }
        ImageLabel imageLabel = new ImageLabel(120,120,staff.getStaffPicture());
        imageLabel.setOpaque(false);
        JLabel dpLabel = new JLabel( departmentService.getOneDept(staff.getDeptNumber()).getDeptName() + "-" + staff.getStaffPost());
        dpLabel.setOpaque(false);
        Style.setLabelStyle(dpLabel);
        panels[0].add(imageLabel);
        panels[1].add(dpLabel);
        panels[2].add(checkButton);
        panels[2].add(modifyButton);

        for (int i=0;i<panels.length;i++){
            showPanel.add(panels[i]);
        }
        this.add(showPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton){
            qpi = new QueryPersonalInfo(staff.getStaffNumber());
            JFrame frame = new JFrame("个人信息窗体");
            frame.getContentPane().add(qpi);
            frame.setLocationRelativeTo(null);
            frame.setSize(700,700);
            frame.setVisible(true);
        }
        if (e.getSource() == modifyButton){
            mpi = new ModifyPersonInfo(staff);
            JFrame frame = new JFrame("修改个人信息");
            frame.getContentPane().add(mpi);
            frame.setLocationRelativeTo(null);
            frame.setSize(700,700);
            frame.setVisible(true);
        }
    }
}
