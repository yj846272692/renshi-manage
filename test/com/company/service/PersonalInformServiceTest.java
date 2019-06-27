package com.company.service;

import com.company.factory.ServiceFactory;
import com.company.entity.PersonalInform;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class PersonalInformServiceTest {


    private PersonalInformService personalInformService;
    @Before
    public void setUp() throws Exception {
        personalInformService = ServiceFactory.getPersonalInformServiceInstance();
    }

    @Test
    public void getAllInforms() throws Exception {
        List<PersonalInform> list = personalInformService.getAllInforms();
        list.forEach(personalInform -> System.out.println(personalInform));
    }

    @Test
    public void getAllInformBySN() throws Exception {
        List<PersonalInform> list = personalInformService.getAllInformBySN("20010");
        list.forEach(personalInform -> System.out.println(personalInform));
    }

}