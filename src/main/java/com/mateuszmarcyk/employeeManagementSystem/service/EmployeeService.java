package com.mateuszmarcyk.employeeManagementSystem.service;

import com.mateuszmarcyk.employeeManagementSystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findById(Long id);

    List<Employee> findAll();

    Employee save(Employee employee);


    void deleteById(Long id);

}
