package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/employees/add")
    public ResponseEntity<String> addEmployeeJson(@RequestBody Employee employee) {
        service.addEmployee(employee);
        return ResponseEntity.ok("Employee added successfully!");
    }

    @GetMapping("/employees/view")
    public ResponseEntity<List<Employee>> listEmployeesApi() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @PostMapping("/api/employees")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        service.addEmployee(employee);
        return ResponseEntity.ok("Employee added successfully!");
    }

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(service.getAllEmployees());
    }

    @GetMapping("/api/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/api/employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        if (service.updateEmployee(id, employee)) {
            return ResponseEntity.ok("Employee updated successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        if (service.deleteEmployee(id)) {
            return ResponseEntity.ok("Employee deleted successfully!");
        }
        return ResponseEntity.notFound().build();
    }
} 