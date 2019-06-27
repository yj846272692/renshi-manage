package panelUtils;




import com.company.factory.ServiceFactory;
import com.company.entity.AllInform;
import com.company.entity.Staff;
import com.company.service.AllInformService;
import com.company.utils.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**

 * 全体通知的发送
 */
public class InsertAllPanel extends JPanel implements ActionListener {
    private JPanel topPanel;
    private JButton confirmButton;
    private JButton dateButton;
    private JLabel showLabel;
    private JTextArea textArea;
    private String dateString;
    private Staff staff;
    private MyTextField textField;
    private AllInformService allInformService = ServiceFactory.getAllInformServiceInstance();

    public InsertAllPanel(Staff staff) {
        this.staff = staff;
        this.setLayout(new BorderLayout());
        init();
    }



    private void init() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        confirmButton = new JButton();
        dateButton = new JButton();
        showLabel = new JLabel();
        textArea = new JTextArea();
        Font font = new Font("微软雅黑",Font.BOLD,60);
        textArea.setFont(font);
        textField = new MyTextField();
        textField.setVisible(true);
        textField.setText("请输入标题");
        dateButton.setText("发送时间");
        confirmButton.setText("发 送");
        textArea.setText("请输入通知内容");
        Style.setBigButtonStyle(dateButton);
        Style.setFieldStyle(textField);
        Style.setBigButtonStyle(confirmButton);
        dateButton.addActionListener(this);
        confirmButton.addActionListener(this);
        topPanel.add(dateButton);
        topPanel.add(showLabel);
        topPanel.add(textField);
        topPanel.add(confirmButton);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(textArea,BorderLayout.CENTER);

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                String s = textField.getText();
                if(s.length() >= 10){
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dateButton){
            JTextField txtDate = new JTextField();
            //弹出时间选择框
            new DialogDatePicker(true, txtDate, 550, 250);
            //获得日期
            dateString = txtDate.getText();
            showLabel.setText(dateString);
        }
        if (e.getSource() == confirmButton){
            String content = textArea.getText(); //内容
            String title = textField.getText(); //标题
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = sdf.parse(dateString);
                java.sql.Date sentTime = new java.sql.Date(date.getTime());//时间
                AllInform allInform = new AllInform(staff.getStaffNumber(),title,content,sentTime);
                allInformService.addOneInform(allInform);
                JOptionPane.showMessageDialog(null,"发送成功");
                textArea.setText(" ");
                textField.setText("请输入标题");
            } catch (ParseException e1) {
                JOptionPane.showMessageDialog(null,"请选择时间");
            }catch (NullPointerException e1){
                JOptionPane.showMessageDialog(null,"请选择时间");

            }

        }
    }

    public static void main(String[] args) throws SQLException {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("测试窗体");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        Staff staff = ServiceFactory.getStaffServiceInstance().getOndStaff("20010");

        frame.add(new InsertAllPanel(staff));

        frame.setVisible(true);
    }



}