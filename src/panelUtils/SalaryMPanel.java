package panelUtils;



import com.company.entity.Staff;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SalaryMPanel extends JPanel implements ActionListener {
    private Staff staff;
    private JPanel StopButtonPanel;
    private JPanel contentCardPanel;
    private java.util.List<String> itemList;
    private CardLayout cardLayout;
    private JButton[] itemButtons = null;
    private AllStaffSalaryInfoPanel assip;
    private AccountSalaryPanel asp;
    private OwnSalaryPanel osp;

    public void setItemList(java.util.List<String> itemList) {
        this.itemList = itemList;
    }

    public String getPanelName() {
        return "salary";
    }

    public SalaryMPanel(java.util.List<String> itemList,Staff staff) {
        this.itemList = itemList;
        this.staff = staff;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(102, 204, 204));
        init();
    }

    public void init(){
        StopButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        StopButtonPanel.setBackground(new Color(102, 204, 204));
        contentCardPanel = new JPanel();
        cardLayout = new CardLayout();
        contentCardPanel.setLayout(cardLayout);
        itemButtons = new JButton[itemList.size()];
        for (int i=0;i<itemList.size();i++){
            itemButtons[i] = new JButton(itemList.get(i));
            Style.setBigButtonStyle(itemButtons[i]);
            itemButtons[i].addActionListener(this);
            if ("查询全体工资".equals(itemList.get(i))){
                assip = new AllStaffSalaryInfoPanel();
                contentCardPanel.add(assip,"panel" + i);
            }
            if ("核算工资".equals(itemList.get(i))){
                asp = new AccountSalaryPanel();
                contentCardPanel.add(asp,"panel" + i);
            }
            if ("查询个人工资".equals(itemList.get(i))){
                osp = new OwnSalaryPanel(staff);
                contentCardPanel.add(osp,"panel" + i);
            }

        }



        for (int i=0;i<itemButtons.length;i++){
            StopButtonPanel.add(itemButtons[i]);
        }
        this.add(StopButtonPanel,BorderLayout.NORTH);
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