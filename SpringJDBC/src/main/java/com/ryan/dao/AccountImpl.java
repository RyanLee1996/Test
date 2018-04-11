package com.ryan.dao;

import com.ryan.exception.AccountExceptoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("account")
public class AccountImpl implements DAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void update(String name,double price) {
        String sql = "update Account set balance = balance - ? where name = ?";
        jdbcTemplate.update(sql, price,name);
    }

    /**
     *
     * @param name
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public double get(String name) {
        String sql2 = "select balance from account where name = ?";
        return jdbcTemplate.queryForObject(sql2, Double.class,name);
    }

}
