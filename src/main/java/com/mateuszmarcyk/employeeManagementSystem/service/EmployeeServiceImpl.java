package com.mateuszmarcyk.employeeManagementSystem.service;

import com.mateuszmarcyk.employeeManagementSystem.dao.EmployeeRepository;
import com.mateuszmarcyk.employeeManagementSystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee findById(Long id) {

        Optional<Employee> foundEmployee = employeeRepository.findById(id);

        return foundEmployee.orElseThrow(() -> new RuntimeException("Employee with id: %s not found".formatted(id)));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }


    @Override
    public void deleteById(Long id) {
        Optional<Employee> foundEmployee = employeeRepository.findById(id);

        employeeRepository.deleteById(id);

    }
}
