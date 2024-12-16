package com.practice.api.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.api.dto.Employee;
import com.practice.api.service.EmployeeService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService Em_svc;
    
    @GetMapping("getAll")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(Em_svc.getAll());
    }

    @GetMapping("get")
    public ResponseEntity<Employee> get(@RequestParam int id) {
        return ResponseEntity.status(HttpStatus.OK).body(Em_svc.getById(id));
    }

    @PostMapping("new")
    public ResponseEntity<Integer> newEmployee(@RequestBody Employee entity) {
        return ResponseEntity.status(HttpStatus.OK).body(Em_svc.newEmployee(entity));
    }
    
    
    
}
