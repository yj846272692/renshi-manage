package com.company.factory;


import com.company.dao.*;
import com.company.dao.impl.*;

/**
 * DAO工厂
 */
public class DAOFactory {
    public static LimitGroupDAO getLimitGroupDAOInstance(){
        return new LimitGroupDAOImpl();
    }
    public static LimitItemDAO getLimitItemDAOInstance(){
        return new LimitItemDAOImpl();
    }
    public static LimitMapDAO getLimitMapDAOInstance(){
        return new LimitMapDAOImpl();
    }
    public static LimitDAO getLimitDAOInstance(){
        return new LimitDAOImpl();
    }


    public static LoginDAO getLoginDAOInstance(){
        return new LoginDAOImpl();
    }

    public  static  LoginDAO getLogin(){return  new LoginDAOImpl();}


    public static StaffDAO getStaffDAOInstance(){
        return new StaffDAOImpl();
    }
    public static CheckDAO getCheckDAOInstance(){
        return new CheckDAOImpl();
    }
    public static AllInformDAO getAllInformDAOInstance(){
        return new AllInformDAOImpl();
    }
    public static DepartmentDAO getDepartmentDAOInstance(){
        return new DepartmentDAOImpl();
    }
    public static SalaryDAO getSalaryDAOInstance(){
        return new SalaryDAOImpl();
    }
    public static PersonalInformDAO getPersonalInformDAOInstance(){
        return new PersonalInformDAOImpl();
    }
    public static CountDAO getCountDAOInstance(){
        return new CountDAOImpl();
    }
    public static CheckStatisticsDAO getCheckStatisticsDAOInstance(){
        return new CheckStatisticsDAOImpl();
    }
}