package com.practice.api.utils;

import org.springframework.jdbc.core.RowMapper;

import com.practice.api.dto.*;

public class RwMp {

    public static RowMapper<Employee> employeeMp(){
            return (rs, _)-> new Employee(
                rs.getInt("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getTimestamp("hire_date").toLocalDateTime(),
                rs.getInt("job_id"),
                rs.getDouble("salary"),
                (Integer)rs.getObject("manager_id"),
                rs.getInt("department_id")
            );
    }
          
}
