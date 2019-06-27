package com.company.utils;


import com.company.factory.ServiceFactory;
import com.company.entity.Salary;
import com.company.service.DepartmentService;
import com.company.service.StaffService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.List;

/**

 * 工资详情一键导出到本地磁盘
 */
public class SalaryExportExcel {
    public static void exportData(List<Salary> list) {
        DepartmentService service = ServiceFactory.getDepartmentServiceInstance();
        StaffService staffService = ServiceFactory.getStaffServiceInstance();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("工资情况表");
        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(0);
		/* 列名 */
        String[] titles = { "工号","姓名","职称","基础工资","补发工资","应扣工资","个人所得税",
                "社会保险","公积金","最终工资","时间"};
		/* for循环生成列名 */
        for (int i = 0; i < titles.length; i++) {
            hssfCell = hssfRow.createCell(i);
            hssfCell.setCellValue(titles[i]);
        }
		/* 填充数据 */
        int rowIndex = 1;
        for (Salary salary : list) {
            hssfRow = hssfSheet.createRow(rowIndex);
            hssfCell = hssfRow.createCell(0);
            hssfCell.setCellValue(salary.getStaffNumber());
            hssfCell = hssfRow.createCell(1);
            hssfCell.setCellValue(staffService.getOndStaff(salary.getStaffNumber()).getStaffName());
            hssfCell = hssfRow.createCell(2);
            hssfCell.setCellValue(service.getOneDept(staffService.getOndStaff(salary.getStaffNumber()).getDeptNumber()).getDeptName());
            hssfCell = hssfRow.createCell(3);
            hssfCell.setCellValue(salary.getBasicSalary());
            hssfCell = hssfRow.createCell(4);
            hssfCell.setCellValue(salary.getBfSalary());
            hssfCell = hssfRow.createCell(5);
            hssfCell.setCellValue(salary.getDeductSalary());
            hssfCell = hssfRow.createCell(6);
            hssfCell.setCellValue(salary.getPersonalTax());
            hssfCell = hssfRow.createCell(7);
            hssfCell.setCellValue(salary.getSocialSec());
            hssfCell = hssfRow.createCell(8);
            hssfCell.setCellValue(salary.getReservedFunds());
            hssfCell = hssfRow.createCell(9);
            hssfCell.setCellValue(salary.getFinalSalary());
            hssfCell = hssfRow.createCell(10);
            hssfCell.setCellValue(salary.getTime().toString());
            rowIndex++;
        }
        File file = new File("D:\\exportSalary.xls");
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
