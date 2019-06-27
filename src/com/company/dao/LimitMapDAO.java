package com.company.dao;



import com.company.entity.LimitMap;

import java.sql.SQLException;

/**

 * 权限映射接口
 */
public interface LimitMapDAO {

    /**
     * 根据staffNumber得到权限映射
     * @param staffNumber
     * @return
     * @throws SQLException
     */
    LimitMap getLimitMap(String staffNumber) throws SQLException;

    /**
     * 增加一个权限映射表
     * @param limitMap
     * @return
     * @throws SQLException
     */
    int addOneLimitMap(LimitMap limitMap) throws SQLException;

    /**
     * 删除一个权限映射表
     * @param staffNumber
     * @param itemID
     * @return
     * @throws SQLException
     */
    int deleteOneLimitMap(String staffNumber,String itemID) throws SQLException;

}
