package panelUtils;



import com.company.entity.Staff;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class AttencePanel extends JPanel implements ActionListener{
    private JPanel AtopButtonPanel;
    private JPanel contentCardPanel;
    private java.util.List<String> itemList;
    private Staff staff;
    private CardLayout cardLayout;
    private JButton[] itemButtons = null;
    private CheckPanel cp;
    private OwnCheckPanel ocp;
    private DaliyCheckPanel dcp;
    private CalendarPanel calendarPanel;
    private PersonalCalendar pc;
    private MyCheckTestPanel mctp;

    public void setItemList(java.util.List<String> itemList) {
        this.itemList = itemList;
    }

    public String getPanelName() {
        return "attence";
    }

    public AttencePanel(java.util.List<String> itemList,Staff staff) {
        this.itemList = itemList;
        this.staff = staff;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(102, 204, 204));
        init();
    }


    public void init(){
        AtopButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        AtopButtonPanel.setBackground(new Color(102, 204, 204));
        contentCardPanel = new JPanel();
        cardLayout = new CardLayout();
        contentCardPanel.setLayout(cardLayout);
        itemButtons = new JButton[itemList.size()];
        for (int i=0;i<itemList.size();i++){
            itemButtons[i] = new JButton(itemList.get(i));
            itemButtons[i].setPreferredSize(new Dimension(150,50));
            Style.setBigButtonStyle(itemButtons[i]);

            itemButtons[i].addActionListener(this);
            if ("查询全体考勤".equals(itemList.get(i))){
                cp = new CheckPanel();
                contentCardPanel.add(cp,"panel" + i);
            }
            if ("查询个人考勤".equals(itemList.get(i))){
                pc = new PersonalCalendar(staff.getStaffNumber());
                contentCardPanel.add(pc,"panel" + i);
            }
            if ("每日考勤".equals(itemList.get(i))){
                mctp = new MyCheckTestPanel();
                contentCardPanel.add(mctp,"panel" + i);
            }

        }



        for (int i=0;i<itemButtons.length;i++){
            AtopButtonPanel.add(itemButtons[i]);
        }
        this.add(AtopButtonPanel,BorderLayout.NORTH);
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
