package com.mateuszmarcyk.employeeManagementSystem.controller;

import com.mateuszmarcyk.employeeManagementSystem.entity.Employee;
import com.mateuszmarcyk.employeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/list")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "/employees/employee-list";
    }

    @GetMapping("/save")
    public String showAddEmployeeForm(Model model) {

        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("title", "Add Employee");

        return "/employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(Model model, @Valid @ModelAttribute Employee employee, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            String title = "Add Employee";

            if (employee.getId() != null) {
                title = "Edit Employee";
            }

            model.addAttribute("title", title);

            return "employees/employee-form";
        }

            employeeService.save(employee);

            return "redirect:/employees/list";

    }

    @GetMapping("/edit/{employeeId}")
    public String showEmployeeEditFrom(Model model, @PathVariable Long employeeId) {
        Employee employee = employeeService.findById(employeeId);

        model.addAttribute("employee", employee);
        model.addAttribute("title", "Edit Employee");

        return "employees/employee-form";
    }

    @GetMapping("/delete/{employeeId}")
    public String deleteEmployeeById(@PathVariable Long employeeId) {

        employeeService.deleteById(employeeId);

        return "redirect:/employees/list";
    }


}
