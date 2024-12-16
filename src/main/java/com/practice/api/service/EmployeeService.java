package com.practice.api.service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.practice.api.dto.Employee;
import com.practice.api.utils.RwMp;

@Service
public class EmployeeService {
    
    @Autowired
    JdbcTemplate conn;

    private static final String getById= "SELECT * FROM employees WHERE employee_id= ?";
    private static final String getAll= "SELECT * FROM employees";
    private static final String newEmployee= "INSERT INTO practice.employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id) VALUES (?,?,?,?,?,?,?,?,?);";
    

    public List<Employee> getAll(){
        return conn.query(getAll, RwMp.employeeMp());
    }
    public Employee getById(int id){
        return conn.queryForObject(getById, RwMp.employeeMp(), id);
    }

    @SuppressWarnings("null")
    public int newEmployee(Employee e){

        KeyHolder id= new GeneratedKeyHolder(); 
        // conn.update(newEmployee, e.f_name(), e.l_name(), e.email(), e.phone(), e.hire_date(), e.job_id(), e.salary(), e.manager_id(), e.department_id());
        conn.update( 
            connection -> {
                PreparedStatement ps = connection.prepareStatement(newEmployee, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, e.f_name());
                ps.setString(2, e.l_name());
                ps.setString(3, e.email());
                ps.setString(4, e.phone());
                ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                ps.setInt(6, e.job_id());
                ps.setDouble(7, e.salary());
                ps.setObject(8, e.manager_id());
                ps.setInt(9, e.department_id());
                return ps;
            }, 
            id
        );
        return id.getKey().intValue();
    }
}
