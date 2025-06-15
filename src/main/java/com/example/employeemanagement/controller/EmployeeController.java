package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/v1/view")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employee-list";
    }

    @GetMapping("/v1/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/v1/add")
    public String addEmployeeViaForm(@ModelAttribute Employee employee) {
        service.addEmployee(employee);
        return "redirect:/employees/v1/view";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addEmployeeJson(@RequestBody Employee employee) {
        service.addEmployee(employee);
        return "Employee added successfully!";
    }

    @GetMapping("/view")
    @ResponseBody
    public List<Employee> listEmployeesApi() {
        return service.getAllEmployees();
    }
}
