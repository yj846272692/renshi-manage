package com.company.service;



import com.company.entity.Salary;

import java.sql.SQLException;
import java.util.List;

/**
 * 工资详细服务接口
 */
public interface SalaryService {

    /**
     * 得到所有的工资信息
     * @return
     */
    List<Salary> getAllSalary();

    /**
     * 根据员工工号删除本人的工资详细条目
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    int deleteSalaryBySN(String staffNumber);


    /**
     * 增加一条工资记录
     * @param salary
     * @return
     * @throws SQLException
     */
    int addOneSalary(Salary salary);


    /**
     * 根据个人工号得到个人工资
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    Salary getOneSalary(String staffNumber);



    /**
     * 根据工号得到这个人的所有工资详细
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    List<Salary> getAllpersonSalary(String staffNumber);


    /**
     * 根据工号的到这个人工资发放的时间
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    List<String> getPersonalSalaryTime(String staffNumber);



}
