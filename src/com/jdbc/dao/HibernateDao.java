package com.jdbc.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public int getEmployee(){
        String hql = "select count (*) from Employee";
        Query query = getSessionFactory().openSession().createQuery(hql);
        return ((Long)query.uniqueResult()).intValue();
    }
    
}
