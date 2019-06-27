package com.company.utils;



import com.company.factory.ServiceFactory;
import com.company.entity.Check;
import com.company.service.StaffService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * 导出文件工具类
 */
public class ExportExcel {
    public static void exportData(List<Check> list) {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();//创建一个工作簿对象
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("考勤情况表");//创建一个工作表对象
        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(0);
        StaffService staffService = ServiceFactory.getStaffServiceInstance();
		/* 列名 */
        String[] titles = { "工号","姓名","考勤情况","考勤日期" };
		/* for循环生成列名 */
        for (int i = 0; i < titles.length; i++) {
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(titles[i]);
        }
		/* 填充数据 */
        int rowIndex = 1;
        for (Check check : list) {
            hssfRow = hssfSheet.createRow(rowIndex);

            hssfCell = hssfRow.createCell(0);
            hssfCell.setCellValue(check.getStaffNumber());

            String staffName = staffService.getOndStaff(check.getStaffNumber()).getStaffName();
            hssfCell = hssfRow.createCell(1);
            hssfCell.setCellValue(staffName);

            hssfCell = hssfRow.createCell(2);
            hssfCell.setCellValue(check.getCheckType());

            hssfCell = hssfRow.createCell(3);
            hssfCell.setCellValue(check.getCheckDate().toString());
            rowIndex++;
        }
        File file = new File("D:/check.xls");
        OutputStream output = null;
        try {
            output = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            hssfWorkbook.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
