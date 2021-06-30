package com.rookie.employee.controller;

import com.rookie.employee.entity.Employee;
import com.rookie.employee.exception.ResourceNotFoundException;
import com.rookie.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //get employee
    @GetMapping("")
    public List<Employee> getAllEmployee(){
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    //get employee by id
    @GetMapping("/{id}")
    public Optional<Employee> findEmployee(@PathVariable("id") Long employeeId) throws ResourceNotFoundException {
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployee(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee not found for this id: " + employeeId)));

        return employeeService.getEmployee(employeeId);
    }

    //save employee
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
//
//    //update
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @RequestBody Employee employeeDetail) throws ResourceNotFoundException{
        Employee employee = employeeService.getEmployee(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee not found for this id: " +employeeId));

        employee.setEmail(employeeDetail.getEmail());
        employee.setFirstName(employeeDetail.getFirstName());
        employee.setLastName(employeeDetail.getLastName());
        employee.setRole(employeeDetail.getRole());

        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }
//
//    //delete
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        employeeService.deleteEmployee(employeeId);
        Map<String, Boolean> reponse = new HashMap<>();
        reponse.put("deleted", Boolean.TRUE);

        return reponse;
    }
}
