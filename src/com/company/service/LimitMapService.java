package com.company.service;

import com.company.entity.LimitMap;

import java.sql.SQLException;

/**
 * 权限映射表的服务接口
 */
public interface LimitMapService {
    /**
     * 增加一个权限映射表
     * @param limitMap
     * @return
     * @throws SQLException
     */
    int addOneLimitMap(LimitMap limitMap);

    /**
     * 删除一个权限映射表
     * @param staffNumber
     * @param itemID
     * @return
     * @throws SQLException
     */
    int deleteOneLimitMap(String staffNumber,String itemID);
}
