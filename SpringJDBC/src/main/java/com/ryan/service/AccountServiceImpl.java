package com.ryan.service;

import com.ryan.dao.AccountImpl;
import com.ryan.dao.BookShopImpl;
import com.ryan.exception.AccountExceptoin;
import com.ryan.exception.BookShopExceptoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
public class AccountServiceImpl {
    @Autowired
    private AccountImpl account;
    @Autowired
    private BookShopImpl bookShop;
    @Transactional
    public void pay(String name,String isbn,int number){
        double price = bookShop.getPrice(isbn)*number;
        int count = bookShop.getStock(isbn);
        if (count < number){
            throw new BookShopExceptoin("此书库存不足。");
        }
        bookShop.update(isbn,number);
        double money = account.get(name);
        if (money - price < 0){
            throw new AccountExceptoin("您的余额不足，请充值！");
        }
        account.update(name,price);
    }
}
