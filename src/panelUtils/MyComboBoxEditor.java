package panelUtils;

import javax.swing.*;

/**
 * 将字符串转换成下拉框
 */
public class MyComboBoxEditor extends DefaultCellEditor {
        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }
