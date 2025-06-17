package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    boolean updateEmployee(Long id, Employee employee);
    boolean deleteEmployee(Long id);
}
