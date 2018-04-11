package com.ryan.dao;

import com.ryan.exception.BookShopExceptoin;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("bookShop")
public class BookShopImpl implements DAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Transactional
    public void update(String isbn,int number) {
        String sql = "update Stock set count = count - ? where isbn = ?";
        jdbcTemplate.update(sql,number,isbn);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public double getPrice(String isbn) {
        String sql = "select price from book where isbn = ?";
        return jdbcTemplate.queryForObject(sql, Double.class,isbn);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int getStock(String isbn){
        String sql2 = "select count from Stock where isbn = ?";
        return jdbcTemplate.queryForObject(sql2, Integer.class,isbn);
    }
}
