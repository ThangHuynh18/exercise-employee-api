package com.rookie.employee.service;

import com.rookie.employee.entity.Employee;
import com.rookie.employee.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> retrieveEmployees();

    public Optional<Employee> getEmployee(Long employeeId);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployee(Long employeeId) throws ResourceNotFoundException;

    public Employee updateEmployee(Employee employee);
}
