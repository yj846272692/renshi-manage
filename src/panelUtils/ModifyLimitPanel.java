package panelUtils;

import com.company.factory.ServiceFactory;
import com.company.entity.LimitMap;
import com.company.entity.Staff;
import com.company.service.LimitItemService;
import com.company.service.LimitMapService;
import com.company.service.LimitService;
import com.company.service.StaffService;
import com.company.utils.MySuccessDialog;
import com.company.utils.Style;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 修改权限的panel的封装
 */
public class ModifyLimitPanel extends JPanel implements ItemListener,ActionListener{
    private Staff staff;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;
    private JComboBox numberCBox;
    private JComboBox nameCBox;
    private JComboBox postCBox;
    private List<Staff> staffList;
    private String selectedStaffNumber;
    //权限项的复选框
    private JCheckBox[] staffCheckBox;
    private JCheckBox[] checkCheckBox;
    private JCheckBox[] salaryCheckBox;
    private JCheckBox[] informCheckBox;
    private JCheckBox[] limitCheckBox;
    //修改按钮
    private JButton modifyButton;
    private JButton flashButton;
    //以下是查询某个人所拥有的权限组及其组下的权限项
    private List<String> groupNameList;
    private Map<String,List<String>> listMap;
    private LimitService limitService = ServiceFactory.getLimitServiceInstance();
    private StaffService staffService = ServiceFactory.getStaffServiceInstance();


    public ModifyLimitPanel(Staff staff){
        this.staff = staff;
        this.setLayout(new GridLayout(0,1));
        init();
    }

    public void init(){
        staffList = staffService.getAll(staff.getStaffNumber());
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBackground(Color.WHITE);
        centerPanel = new JPanel(new GridLayout(5,1));
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        //两个下拉列表框的初始化
        initComboxBox();
        //以下是中间面板的内容设置
        initCenterPanel();
        //更改按钮的初始化
        modifyButton = new JButton("确认更改");
        flashButton = new JButton("刷新");
        modifyButton.addActionListener(this);
        flashButton.addActionListener(this);
        Style.setBigButtonStyle(modifyButton);
        Style.setBigButtonStyle(flashButton);
        topPanel.add(numberCBox);
        topPanel.add(nameCBox);
        topPanel.add(postCBox);
        topPanel.add(flashButton);
        bottomPanel.add(modifyButton);
        this.add(topPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.SOUTH);
    }

    public void initCenterPanel(){
        String[] groupNames = {"员工管理","考勤管理","工资管理","通知管理","权限管理"};
        JPanel[] panels = new JPanel[groupNames.length];
        for (int i=0;i<panels.length;i++){
            panels[i] = new JPanel(new FlowLayout(FlowLayout.LEFT,30,0));
            panels[i].setBackground(Color.lightGray);
        }
        JLabel[] groupNamesLabel = new JLabel[groupNames.length];
        for (int i=0;i<groupNames.length;i++){
            groupNamesLabel[i] = new JLabel(groupNames[i]);
            Style.setLabelStyle(groupNamesLabel[i]);
            groupNamesLabel[i].setOpaque(false);
            panels[i].add(groupNamesLabel[i]);
        }

        String[] staffItems = {"查询全体档案","增加员工"};
        staffCheckBox = new JCheckBox[staffItems.length];
        for (int i=0;i<staffItems.length;i++){
            staffCheckBox[i] = new JCheckBox(staffItems[i]);
            staffCheckBox[i].setOpaque(false);
            Style.setCheckBoxStyle(staffCheckBox[i]);
            panels[0].add(staffCheckBox[i]);
        }

        String[] checkItems = {"查询全体考勤","查询个人考勤","每日考勤"};
        checkCheckBox = new JCheckBox[checkItems.length];
        for (int i=0;i<checkItems.length;i++){
            checkCheckBox[i] = new JCheckBox(checkItems[i]);
            checkCheckBox[i].setOpaque(false);
            Style.setCheckBoxStyle(checkCheckBox[i]);
            panels[1].add(checkCheckBox[i]);
        }

        String[] salaryItems = {"查询全体工资","查询个人工资","核算工资"};
        salaryCheckBox = new JCheckBox[salaryItems.length];
        for (int i=0;i<salaryItems.length;i++){
            salaryCheckBox[i] = new JCheckBox(salaryItems[i]);
            salaryCheckBox[i].setOpaque(false);
            Style.setCheckBoxStyle(salaryCheckBox[i]);
            panels[2].add(salaryCheckBox[i]);
        }

        String[] informItems = {"查询个人私信","发送个人私信","发送全体通知"};
        informCheckBox = new JCheckBox[informItems.length];
        for (int i=0;i<informItems.length;i++){
            informCheckBox[i] = new JCheckBox(informItems[i]);
            informCheckBox[i].setOpaque(false);
            Style.setCheckBoxStyle(informCheckBox[i]);
            panels[3].add(informCheckBox[i]);
        }

        String[] limitItems = {"修改权限"};
        limitCheckBox = new JCheckBox[limitItems.length];
        for (int i=0;i<limitItems.length;i++){
            limitCheckBox[i] = new JCheckBox(limitItems[i]);
            limitCheckBox[i].setOpaque(false);
            Style.setCheckBoxStyle(limitCheckBox[i]);
            panels[4].add(limitCheckBox[i]);
        }

        modifyLimit();

        for (int i=0;i<panels.length;i++){
            centerPanel.add(panels[i]);
        }
    }
    public void modifyLimit(){
        groupNameList = limitService.getGroupName(selectedStaffNumber);
        listMap = limitService.getItemName(groupNameList,selectedStaffNumber);
        for (Map.Entry<String,List<String>> entry : listMap.entrySet()){
            String groupName = entry.getKey();
            List<String> itemList = entry.getValue();
            Iterator<String> iterator = itemList.iterator();
            if ("员  工".equals(groupName)){
                while (iterator.hasNext()){
                    String itemName = iterator.next();
                    if ("查询全体档案".equals(itemName)){
                        if(!staffCheckBox[0].isSelected()){
                            staffCheckBox[0].setSelected(true);
                        }else {
                            staffCheckBox[0].setSelected(false);
                        }
                    }
                    if ("增加员工".equals(itemName)){
                        if (!staffCheckBox[1].isSelected()){
                            staffCheckBox[1].setSelected(true);
                        }else {
                            staffCheckBox[1].setSelected(false);
                        }
                    }
                }
            }

            if ("考  勤".equals(groupName)){
                while (iterator.hasNext()){
                    String itemName = iterator.next();
                    if ("查询全体考勤".equals(itemName)){
                        if (!checkCheckBox[0].isSelected()){
                            checkCheckBox[0].setSelected(true);
                        }else {
                            checkCheckBox[0].setSelected(false);
                        }
                    }
                    if ("查询个人考勤".equals(itemName)){
                        if (!checkCheckBox[1].isSelected()){
                            checkCheckBox[1].setSelected(true);
                        }else {
                            checkCheckBox[1].setSelected(false);
                        }
                    }
                    if ("每日考勤".equals(itemName)){
                        if (!checkCheckBox[2].isSelected()){
                            checkCheckBox[2].setSelected(true);
                        }else {
                            checkCheckBox[2].setSelected(false);
                        }
                    }
                }
            }

            if ("工  资".equals(groupName)){
                while (iterator.hasNext()){
                    String itemName = iterator.next();
                    if ("查询全体工资".equals(itemName)){
                        if (!salaryCheckBox[0].isSelected()){
                            salaryCheckBox[0].setSelected(true);
                        }else {
                            salaryCheckBox[0].setSelected(false);
                        }
                    }
                    if ("查询个人工资".equals(itemName)){
                        if (!salaryCheckBox[1].isSelected()){
                            salaryCheckBox[1].setSelected(true);
                        }else {
                            salaryCheckBox[1].setSelected(false);
                        }
                    }
                    if("核算工资".equals(itemName)){
                        if (!salaryCheckBox[2].isSelected()){
                            salaryCheckBox[2].setSelected(true);
                        }else {
                            salaryCheckBox[2].setSelected(false);
                        }
                    }
                }
            }

            if ("通  知".equals(groupName)){
                while  (iterator.hasNext()){
                    String itemName = iterator.next();
                    if ("查询个人私信".equals(itemName)){
                        if (!informCheckBox[0].isSelected()){
                            informCheckBox[0].setSelected(true);
                        }else {
                            informCheckBox[0].setSelected(false);
                        }
                    }
                    if("发送个人私信".equals(itemName)){
                        if (!informCheckBox[1].isSelected()){
                            informCheckBox[1].setSelected(true);
                        }else {
                            informCheckBox[1].setSelected(false);
                        }
                    }
                    if ("发送全体通知".equals(itemName)){
                        if (!informCheckBox[2].isSelected()){
                            informCheckBox[2].setSelected(true);
                        }else {
                            informCheckBox[2].setSelected(false);
                        }
                    }
                }
            }

            if ("权  限".equals(groupName)){
                while (iterator.hasNext()){
                    String itemName = iterator.next();
                    if ("修改权限".equals(itemName)){
                        if (!limitCheckBox[0].isSelected()){
                            limitCheckBox[0].setSelected(true);
                        }else {
                            limitCheckBox[0].setSelected(false);
                        }
                    }
                }
            }
        }
    }


    public void initComboxBox(){
        numberCBox = new JComboBox();
        numberCBox.addItemListener(this);
        nameCBox = new JComboBox();
        postCBox = new JComboBox();
        List<String> postNames = staffService.getAllPostName();
        String[] numbers = new String[staffList.size()];
        String[] names = new String[staffList.size()];
        String[] posts = new String[postNames.size()];
        for (int i=0;i<postNames.size();i++){
            posts[i] = new String(postNames.get(i));
        }
        for (int i=0;i<staffList.size();i++){
            String staffNumber = staffList.get(i).getStaffNumber();
            String staffName = staffService.getOndStaff(staffNumber).getStaffName();
            if (!(staff.getStaffNumber().equals(staffNumber))){
                numbers[i] = new String(staffNumber);
                names[i] = new String(staffName);
            }
        }
        DefaultComboBoxModel<String> numberModel = new DefaultComboBoxModel(numbers);
        DefaultComboBoxModel<String> nameModel = new DefaultComboBoxModel(names);
        DefaultComboBoxModel<String> postModel = new DefaultComboBoxModel(posts);
        numberCBox.setModel(numberModel);
        nameCBox.setModel(nameModel);
        postCBox.setModel(postModel);
        selectedStaffNumber = numberCBox.getSelectedItem().toString();
        Style.setFieldStyle(numberCBox);
        Style.setFieldStyle(nameCBox);
        Style.setFieldStyle(postCBox);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        selectedStaffNumber = e.getItem().toString();
        String staffName = staffService.getOndStaff(selectedStaffNumber).getStaffName();
        String staffPost = staffService.getOndStaff(selectedStaffNumber).getStaffPost();
        nameCBox.setSelectedItem(staffName);
        postCBox.setSelectedItem(staffPost);
        modifyLimit();
        centerPanel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modifyButton){
            Map<String,String> itemIDAndItemName = limitService.getItemIDAndName(selectedStaffNumber);
            LimitItemService itemService = ServiceFactory.getLimitItemServiceInstance();
            LimitMapService mapService = ServiceFactory.getLimitMapServiceInstance();
            //判断员工管理权限
            for (int i = 0;i<staffCheckBox.length;i++){
                if (staffCheckBox[i].isSelected()){
                    boolean flag = false;
                    String itemID;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                         itemName = entry.getKey();
                         itemID = entry.getValue();
                        if ( itemName.equals(staffCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        String newItemName = staffCheckBox[i].getText();
                        System.out.println(newItemName);
                        String id = itemService.getLimitItemID(newItemName);
                        System.out.println(id);
                        LimitMap limitMap = new LimitMap(selectedStaffNumber,id);
                        System.out.println("员工管理要增加的权限映射——" + limitMap);
                        mapService.addOneLimitMap(limitMap);
                    }
                }else {
                    boolean flag = false;
                    String itemID = null;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if (itemName.equals(staffCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if (flag == true){
                        mapService.deleteOneLimitMap(selectedStaffNumber,itemID);
                        System.out.println("员工管理要删除的权限映射--" + selectedStaffNumber + "  " + itemID);
                    }
                }
            }
            //判断考勤管理权限
            for (int i=0;i<checkCheckBox.length;i++){
                if (checkCheckBox[i].isSelected()){
                    boolean flag = false;
                    String itemID;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if ( itemName.equals(checkCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        String newItemName = checkCheckBox[i].getText();
                        System.out.println(newItemName);

                        String id = itemService.getLimitItemID(newItemName);
                        System.out.println(id);

                        LimitMap limitMap = new LimitMap(selectedStaffNumber,id);
                        System.out.println("考勤管理要增加的权限映射——" + limitMap);

                        mapService.addOneLimitMap(limitMap);
                    }
                }else {
                    boolean flag = false;
                    String itemID = null;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if (itemName.equals(checkCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if (flag == true){
                        mapService.deleteOneLimitMap(selectedStaffNumber,itemID);
                        System.out.println("考勤管理要删除的权限映射--" + selectedStaffNumber + "  " + itemID);
                    }
                }
            }

            //判断工资管理权限
            for (int i=0;i<salaryCheckBox.length;i++){
                if (salaryCheckBox[i].isSelected()){
                    boolean flag = false;
                    String itemID;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if ( itemName.equals(salaryCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        String newItemName = salaryCheckBox[i].getText();
                        System.out.println(newItemName);

                        String id = itemService.getLimitItemID(newItemName);
                        System.out.println(id);

                        LimitMap limitMap = new LimitMap(selectedStaffNumber,id);
                        System.out.println("工资管理要增加的权限映射——" + limitMap);
                        mapService.addOneLimitMap(limitMap);
                    }
                }else {
                    boolean flag = false;
                    String itemID = null;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if (itemName.equals(salaryCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if (flag == true){
                        mapService.deleteOneLimitMap(selectedStaffNumber,itemID);
                        System.out.println("工资管理要删除的权限映射--" + selectedStaffNumber + "  " + itemID);
                    }
                }
            }
            //通知管理权限
            for (int i=0;i<informCheckBox.length;i++){
                if (informCheckBox[i].isSelected()){
                    boolean flag = false;
                    String itemID;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if ( itemName.equals(informCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        String newItemName = informCheckBox[i].getText();
                        System.out.println(newItemName);

                        String id = itemService.getLimitItemID(newItemName);
                        System.out.println(id);

                        LimitMap limitMap = new LimitMap(selectedStaffNumber,id);
                        mapService.addOneLimitMap(limitMap);
                        System.out.println("通知管理要增加的权限映射——" + limitMap);
                    }
                }else {
                    boolean flag = false;
                    String itemID = null;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if (itemName.equals(informCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if (flag == true){
                        mapService.deleteOneLimitMap(selectedStaffNumber,itemID);
                        System.out.println("通知管理要删除的权限映射--" + selectedStaffNumber + "  " + itemID);
                    }
                }
            }

            //判断权限管理
            for (int i=0;i<limitCheckBox.length;i++){
                if (limitCheckBox[i].isSelected()){
                    boolean flag = false;
                    String itemID;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if ( itemName.equals(limitCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if(flag == false){
                        String newItemName = limitCheckBox[i].getText();
                        System.out.println(newItemName);

                        String id = itemService.getLimitItemID(newItemName);
                        System.out.println(id);

                        LimitMap limitMap = new LimitMap(selectedStaffNumber,id);
                        mapService.addOneLimitMap(limitMap);
                        System.out.println("权限管理要增加的权限映射——" + limitMap);
                    }
                }else {
                    boolean flag = false;
                    String itemID = null;
                    String itemName;
                    for (Map.Entry<String,String> entry : itemIDAndItemName.entrySet()){
                        itemName = entry.getKey();
                        itemID = entry.getValue();
                        if (itemName.equals(limitCheckBox[i].getText())){
                            flag = true;
                        }
                    }
                    if (flag == true){
                        mapService.deleteOneLimitMap(selectedStaffNumber,itemID);
                        System.out.println("权限管理要删除的权限映射--" + selectedStaffNumber + "  " + itemID);
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"权限修改成功");


        }


        if (e.getSource() == flashButton){
            initComboxBox();
            modifyLimit();
            centerPanel.revalidate();
        }
    }


    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.
                    BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }




        Staff staff = ServiceFactory.getStaffServiceInstance().getOndStaff("20010");
        JFrame frame = new JFrame();
        frame.add(new ModifyLimitPanel(staff));
        frame.setSize(700,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}