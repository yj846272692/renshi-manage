package com.company.utils;

import com.company.factory.ServiceFactory;
import com.company.entity.CheckStatistics;
import com.company.service.StaffService;

import java.util.HashMap;
import java.util.Map;

/**
 * 工资核算工具类
 */
public class AccountSalary {
    public static Map<String,Object> getSalaryInform(CheckStatistics statistics){
        StaffService staffService = ServiceFactory.getStaffServiceInstance();
        Map<String,Object> map = new HashMap<>();
        String staffNumber = statistics.getStaffNumber();
        String name = staffService.getOndStaff(statistics.getStaffNumber()).getStaffName();
        String postName = staffService.getOndStaff(statistics.getStaffNumber()).getStaffPost();
        //社保
        double socialSec = 40;
        //公积金
        double reservedFunds = 800;
        //基础工资
        double basicSalary = 0 ;
        if (postName.contains("主管")){
            basicSalary = 8000;
        }else {
            basicSalary = 6000;
        }
        //补发工资，正常上班每天给10元
        double bfSalary = statistics.getNormalCount() * 10;
        //应扣工资，缺勤每天50元，迟到，早退每天30元，请假每天20元
        double deductSalary = statistics.getTruantCount() * 50 + statistics.getLateCount() * 30 + statistics.getLeaveCount()*30 + statistics.getHolidayCount()*20;
        double beforeTaxSalary = basicSalary + bfSalary -deductSalary;//税前工资
        double taxSalary = beforeTaxSalary-3500-socialSec-reservedFunds;//应纳税所得额
        //个人税
        double personTax =0;
        if (taxSalary<=1500){
            personTax = taxSalary * 0.05;
        }
        if (taxSalary>1500 && taxSalary<= 4500){
            personTax = taxSalary * 0.1-105;
        }
        if (taxSalary>4500 && taxSalary<=9000){
            personTax = taxSalary * 0.2 -555;
        }
        //最终工资
        double finalSalary = beforeTaxSalary - personTax;
        map.put("staffNumber",staffNumber);
        map.put("name",name);
        map.put("postName",postName);
        map.put("basicSalary",basicSalary);
        map.put("bfSalary",bfSalary);
        map.put("deductSalary",deductSalary);
        map.put("personTax",personTax);
        map.put("socialSec",socialSec);
        map.put("reservedFunds",reservedFunds);
        map.put("finalSalary",finalSalary);
        return map;
    }
}