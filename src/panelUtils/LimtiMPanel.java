package panelUtils;

import com.company.entity.Staff;
import com.company.utils.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


/**
 * 权限管理界面
 */
public class LimtiMPanel extends JPanel implements ActionListener {
    private Staff staff;
    private JPanel topPanel;
    private JPanel centerCardPanel;
    private CardLayout cardLayout;
    private List<String> itemList;
    private JButton[] itemButtons = null;

    private ModifyLimitPanel mlp;

    public LimtiMPanel(List<String> itemList,Staff staff){
        this.itemList = itemList;
        this.staff = staff;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(102, 204, 204));
        init();
    }
    public void init(){
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(new Color(102, 204, 204));
        centerCardPanel = new JPanel();
        cardLayout = new CardLayout();
        centerCardPanel.setLayout(cardLayout);
        itemButtons = new JButton[itemList.size()];
        for (int i=0;i<itemList.size();i++){
            itemButtons[i] = new JButton(itemList.get(i));
            Style.setBigButtonStyle(itemButtons[i]);
            itemButtons[i].addActionListener(this);
            if ("修改权限".equals(itemList.get(i))){{
                mlp = new ModifyLimitPanel(staff);
                centerCardPanel.add(mlp,"panel" + i);
            }

            }
        }
        for (int i=0;i<itemButtons.length;i++){
            topPanel.add(itemButtons[i]);
        }
        this.add(topPanel,BorderLayout.NORTH);
        this.add(centerCardPanel,BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0;i<itemList.size();i++){
            if (e.getSource() ==itemButtons[i] ){
                cardLayout.show(centerCardPanel,"panel" + i);
            }
        }
    }



}