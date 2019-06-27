package com.company.service;

import java.sql.SQLException;

/**
 * 权限项表的服务接口
 */
public interface LimitItemService {

    /**
     * 根据权限项的名字得到权限项的ID
     * @param itemName
     * @return
     * @throws SQLException
     */
    String getLimitItemID(String itemName);
}
