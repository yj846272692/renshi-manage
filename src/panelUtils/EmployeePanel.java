package panelUtils;





import com.company.factory.ServiceFactory;
import com.company.entity.Staff;
import com.company.service.StaffService;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


public class EmployeePanel extends JPanel implements ActionListener {
    private JPanel EtopButtonPanel;
    private JPanel contentCardPanel;
    private List<String> itemList;
    private Staff staff;
    private CardLayout cardLayout;
    private JButton[] itemButtons = null;
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();
    private AllStaffPanel asp;
    private AddOneStaff aos;


    public String getPanelName() {
        return "employee";
    }

    public EmployeePanel(List<String> itemList,Staff staff) {
        this.staff = staff;
        this.itemList = itemList;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(102, 204, 204));
        init();
    }


    public void init(){
        EtopButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        EtopButtonPanel.setBackground(new Color(102, 204, 204));
        contentCardPanel = new JPanel();
        cardLayout = new CardLayout();
        contentCardPanel.setLayout(cardLayout);
        itemButtons = new JButton[itemList.size()];
        for (int i=0;i<itemList.size();i++){
            itemButtons[i] = new JButton(itemList.get(i));
            Style.setBigButtonStyle(itemButtons[i]);
            itemButtons[i].addActionListener(this);
            if ("查询全体档案".equals(itemList.get(i))){
                asp = new AllStaffPanel();
                contentCardPanel.add(asp,"panel" + i);
            }
            if ("增加员工".equals(itemList.get(i))){
                aos = new AddOneStaff();
                contentCardPanel.add(aos,"panel" + i);
            }
        }



        for (int i=0;i<itemButtons.length;i++){
            EtopButtonPanel.add(itemButtons[i]);
        }
        this.add(EtopButtonPanel,BorderLayout.NORTH);
        this.add(contentCardPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0;i<itemList.size();i++){
            if (e.getSource() ==itemButtons[i] ){
                cardLayout.show(contentCardPanel,"panel" + i);
            }
        }
    }
}
