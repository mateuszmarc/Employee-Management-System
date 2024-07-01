package com.mateuszmarcyk.employeeManagementSystem.controller;

import com.mateuszmarcyk.employeeManagementSystem.entity.Employee;
import com.mateuszmarcyk.employeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "employee-list";
    }

    @GetMapping("/details/{employeeId}")
    @ResponseBody
    public String showEmployeeDetails(@PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return employee.toString();
    }
}
