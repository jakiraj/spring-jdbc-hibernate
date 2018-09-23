package com.jdbc.test;

import com.jdbc.dao.EdSupportDao;
import com.jdbc.dao.EmployeeDao;
import com.jdbc.dao.HibernateDao;
import com.jdbc.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("com/jdbc/test/spring-jdbc.xml");
        HibernateDao employeeDao = (HibernateDao)context.getBean("hibernateDao");
        System.out.println(employeeDao.getEmployee());
//        EdSupportDao employeeDao = (EdSupportDao)context.getBean("edSupportDao");
//        System.out.println(employeeDao.getEmployeebyId(1));
//        EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeDao");
//        System.out.println(employeeDao.saveEmployee(new Employee("Jakir", 25000)));
//        System.out.println(employeeDao.saveEmployeebyPS(new Employee("Raj", 20000)));
//        System.out.println(employeeDao.getEmployeebyId(1));
//        System.out.println(employeeDao.getAllEmployee(1).getName());
//        System.out.println(employeeDao.getAllEmployeeList(1).get(0).getName());
//        System.out.println(employeeDao.saveEmployeebyNPS(new Employee("Hossain", 30000)));
    }
    
}
