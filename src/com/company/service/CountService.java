package com.company.service;



import com.company.entity.EveryTimeSalaryCount;
import com.company.entity.PersonalCheckCount;
import com.company.entity.PersonalSalaryCount;
import com.company.entity.TypeCheckCount;

import java.sql.SQLException;
import java.util.List;

/**

 * 制作考勤统计图的服务接口
 */
public interface CountService {
    /**
     * 得到个人考勤统计
     * @return
     * @throws SQLException
     */
    List<PersonalCheckCount> getCheckCountByType(String staffNumber);


    /**
     * 根据考勤类型来统计人数
     * @return
     * @throws SQLException
     */
    List<TypeCheckCount> getCountBycheckType();


    /**
     * 得到个人工资统计，根据工资类型
     * @return
     * @throws SQLException
     */
    List<PersonalSalaryCount> getPersonalSalaryCountByType(String staffNumber,String time);

    /**
     * 得到一个人每次发放工资时的最终工资
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    List<EveryTimeSalaryCount> getPersonalEveryTimeCount(String staffNumber);
}
