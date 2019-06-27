package panelUtils;



import com.company.entity.Staff;
import com.company.utils.ImageLabel;
import com.company.utils.RounderPanel;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;


public class MyCheckPanel extends RounderPanel implements ItemListener{
    private Staff staff;
    private ImageLabel imageLabel;
    private JLabel staffNumberLabel;
    private JLabel nameLabel;
    private JComboBox comboBox;
    private String typeString;
    private JPanel showPanel;

    public MyCheckPanel(Staff staff){
        super(Color.WHITE,30);
        this.staff = staff;
        this.setPreferredSize(new Dimension(200,170));
        showPanel = new JPanel();
        showPanel.setLayout(new GridLayout(3,1));
        showPanel.setPreferredSize(new Dimension(80,150));
        showPanel.setOpaque(false);
        init();
    }


    public void init(){
//        imageLabel = new ImageLabel(20,20,staff.getStaffPicture());
//        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        staffNumberLabel = new JLabel(staff.getStaffNumber());
        staffNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        Style.setLabelFont(staffNumberLabel);
        nameLabel = new JLabel(staff.getStaffName());
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        Style.setLabelFont(nameLabel);

        String[] types = {"全勤","缺勤","迟到","早退","请假"};
        DefaultComboBoxModel typeModel = new DefaultComboBoxModel(types);
        comboBox = new JComboBox(typeModel);
        typeString = comboBox.getSelectedItem().toString();
        comboBox.addItemListener(this);
        Style.setFieldStyle(comboBox);

//        showPanel.add(imageLabel);
        showPanel.add(staffNumberLabel);
        showPanel.add(nameLabel);
        showPanel.add(comboBox);
        add(showPanel);
    }


    public Map<String,String> getMap(){
        Map<String,String> map = new HashMap<>();
        String stafffNumber = staff.getStaffNumber();
        map.put("staffNumber",stafffNumber);
        map.put("type",typeString);
        return map;
    }

    public void moren(){
        comboBox.setSelectedItem("全勤");
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        typeString = e.getItem().toString();
    }


    public static void main(String[] args) {


    }
}
