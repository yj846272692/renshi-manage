package panelUtils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

/**
 * 创建JTable子类，判断在几行几列添加下拉框
 */
public class TableNew extends JTable {

        int myRow=-1,myCol=-1;
        TableCellEditor myEditor;
        public void setComboCell(int r,int c,TableCellEditor ce){
            this.myRow=r;
            this.myCol=c;
            this.myEditor=ce;
        }
        @Override
        public TableCellEditor getCellEditor(int row, int column) {
            if(row==myRow&&column==myCol&&myEditor!=null) {
                return myEditor;
            }
            return super.getCellEditor(row, column);
        }
}

