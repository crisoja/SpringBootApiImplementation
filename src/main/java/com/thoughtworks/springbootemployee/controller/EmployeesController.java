package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private List<Employee> employees = new ArrayList<>();
    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployeeById(@PathVariable Integer employeeId){
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeeByGender(@RequestParam String gender){
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employees.stream()
                .skip((pageSize - 1) * pageIndex)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employee.setId(employees.size() + 1);
        employees.add(employee);
    }
}
