package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/view")
    public String viewEmployees(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employee-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        service.addEmployee(employee);
        return "redirect:/employees/v1/view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        service.getEmployeeById(id).ifPresent(employee -> model.addAttribute("employee", employee));
        return "edit-employee";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute Employee employee) {
        service.updateEmployee(id, employee);
        return "redirect:/employees/v1/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return "redirect:/employees/v1/view";
    }
}
