package com.ryan.test;


import com.ryan.dao.AccountImpl;
import com.ryan.dao.BookShopImpl;
import com.ryan.service.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JDBCTest {
    private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    private AccountServiceImpl accountService = (AccountServiceImpl) ac.getBean("accountService");
    @Test
    public void test(){
        try{
            accountService.pay("ryan","s0101",10);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
