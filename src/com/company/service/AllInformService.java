package com.company.service;



import com.company.entity.AllInform;

import java.util.List;

/**

 * 全体通知服务接口
 */
public interface AllInformService  {

    /**
     * 得到所有的全体通知
     * @return
     */
    List<AllInform> getAllInform();

    /**
     * 增加一个全体通知
     * @param inform
     * @return
     */
    int addOneInform(AllInform inform);
}
