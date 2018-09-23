package com.jdbc.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class EdSupportDao extends JdbcDaoSupport{
    
    public String getEmployeebyId (int id){
        String sql="select name from employee where id=?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);
    }
}
