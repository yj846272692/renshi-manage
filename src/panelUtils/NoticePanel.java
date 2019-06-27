package panelUtils;



import com.company.entity.Staff;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NoticePanel extends JPanel implements ActionListener{
    private Staff staff;
    private JPanel NtopButtonPanel;
    private JPanel contentCardPanel;
    private List<String> itemList;
    private CardLayout cardLayout;
    private JButton[] itemButtons = null;
    private PersonalInformPanel pip;
    private InsetPIPanel ipip;
    private InsertAllPanel iap;

    public void setItemList(List<String> itemList) {
        this.itemList = itemList;
    }

    public String getPanelName() {
        return "notice";
    }

    public NoticePanel(List<String> itemList,Staff staff) {
        this.itemList = itemList;
        this.staff = staff;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(102, 204, 204));
        init();
    }

    public void init(){
        NtopButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        NtopButtonPanel.setBackground(new Color(102, 204, 204));
        contentCardPanel = new JPanel();
        cardLayout = new CardLayout();
        contentCardPanel.setLayout(cardLayout);
        itemButtons = new JButton[itemList.size()];
        for (int i=0;i<itemList.size();i++){
            itemButtons[i] = new JButton(itemList.get(i));
            Style.setBigButtonStyle(itemButtons[i]);
            itemButtons[i].addActionListener(this);
            if ("查询个人私信".equals(itemList.get(i))){
                pip = new PersonalInformPanel(staff);
                contentCardPanel.add(pip,"panel" + i);
            }
            if ("发送个人私信".equals(itemList.get(i))){
                ipip = new InsetPIPanel(staff);
                contentCardPanel.add(ipip,"panel" + i);
            }
            if ("发送全体通知".equals(itemList.get(i))){
                iap = new InsertAllPanel(staff);
                contentCardPanel.add(iap,"panel" + i);
            }
        }



        for (int i=0;i<itemButtons.length;i++){
            NtopButtonPanel.add(itemButtons[i]);
        }
        this.add(NtopButtonPanel,BorderLayout.NORTH);
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
