package com.jdbc.dao;

import com.jdbc.model.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
            
    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(dataSource);
    }
    
    public int saveEmployee(Employee employee){
        String sql="insert into employee(name, salary) values('"+employee.getName()+"', '"+employee.getSalary()+"')";
        return jdbcTemplate.update(sql);
    }
    //Prepared Statement disadvantage: exact order maintain
    public int saveEmployeebyPS(Employee employee){
        String sql="insert into employee(name, salary) values(?,?)";
        return jdbcTemplate.update(sql, new Object[]{employee.getName(), employee.getSalary()});
    }
    
    //Named Parameter 
    public int saveEmployeebyNPS(Employee employee){
        String sql="insert into employee(name, salary) values(:name,:salary)";
        SqlParameterSource nameParameterSource = new MapSqlParameterSource("name", employee.getName()).addValue("salary", employee.getSalary());
        return namedParameterJdbcTemplate.update(sql, nameParameterSource);
    }
    
    public String getEmployeebyId (int id){
        String sql="select name from employee where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
    }
    
    public Employee getAllEmployee(int id){
        String sql="select * from employee where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeMapper());
    }
    
    public List<Employee> getAllEmployeeList(int id){
        String sql="select * from employee where id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, new EmployeeMapper());
    }
    private static class EmployeeMapper implements RowMapper<Employee>{

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getDouble("salary"));
            return employee;
        }
        
    }
}
