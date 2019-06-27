package com.company.service.impl;



import com.company.dao.PersonalInformDAO;
import com.company.factory.DAOFactory;
import com.company.entity.PersonalInform;
import com.company.service.PersonalInformService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**

 * 个人私信服务实现类
 */
public class PersonalInformServiceImpl implements PersonalInformService {
    private PersonalInformDAO personalInformDAO = DAOFactory.getPersonalInformDAOInstance();

    @Override
    public int addOneInform(PersonalInform personalInform) {
        int n = 0;
        Date date = new Date();
        try {
            n = personalInformDAO.addOneInform(personalInform);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<PersonalInform> getAllInforms() {
        List<PersonalInform> list = null;
        try {
            list = personalInformDAO.getAllInform();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() != 0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<PersonalInform> getAllInformBySN(String receiverNumber) {
        List<PersonalInform> list = null;
        try {
            list = personalInformDAO.getAllInformBySN(receiverNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (list.size() != 0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public PersonalInform getOneInform(Integer id, String receiverNumber) {
        PersonalInform personalInform = null;
        try {
            personalInform = personalInformDAO.getOneInform(id,receiverNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (personalInform != null){
            return personalInform;
        }else {
            return null;
        }
    }

    @Override
    public int updateInform(PersonalInform inform) {
        int n = 0;
        try {
            n = personalInformDAO.updateInform(inform);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int deleteOneInform(Integer id, String receiverNumber) {
        int n =0;
        try {
            n = personalInformDAO.deleteOneInform(id,receiverNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public int deleteInforms(String receiverNumber) {
        int n=0;
        try {
            n = personalInformDAO.deleteInforms(receiverNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}