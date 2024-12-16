package com.practice.api.dto;

import java.time.LocalDateTime;

public record Employee(int id, String f_name, String l_name, String email, String phone, LocalDateTime hire_date, int job_id, double salary, Integer manager_id, int department_id) {
}