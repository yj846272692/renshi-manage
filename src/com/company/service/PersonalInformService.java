package com.company.service;



import com.company.entity.PersonalInform;

import java.sql.SQLException;
import java.util.List;

/**
 * 个人私信服务接口
 */
public interface PersonalInformService {


    /**
     * 增加一个个人私信
     * @param personalInform
     * @return
     */
    int addOneInform(PersonalInform personalInform);

    /**
     * 得到所有的个人私信
     * @return
     */
    List<PersonalInform> getAllInforms();

    /**
     * 根据接收者的工号得到所有的个人私信
     * @return
     */
    List<PersonalInform> getAllInformBySN(String receiverNumber);

    /**
     * 根据id和receiverNumber得到一个私信
     * @param id
     * @param receiverNumber
     * @return
     */
    PersonalInform getOneInform(Integer id, String receiverNumber);

    /**
     * 更新私信
     * @param inform
     * @return
     */
    int updateInform(PersonalInform inform);


    /**
     * 根据id和receiverNumber删除私信
     * @param id
     * @param receiverNumber
     * @return
     */
    int deleteOneInform(Integer id, String receiverNumber);


    /**
     * 根据工号删除与他有关的所有私信
     * @param receiverNumber
     * @return
     * @throws SQLException
     */
    int deleteInforms(String receiverNumber);

}
