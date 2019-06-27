package com.company.dao;



import com.company.entity.LimitItem;

import java.sql.SQLException;

/**

 * 权限项接口
 */
public interface LimitItemDAO {

    /**
     * 根据itemID得到权限项
     * @param itemID
     * @return
     * @throws SQLException
     */
    LimitItem getLimitItem(String itemID) throws SQLException;


    /**
     * 根据权限项的名字得到权限项的ID
     * @param itemName
     * @return
     * @throws SQLException
     */
    String getLimitItemID(String itemName) throws SQLException;
}
