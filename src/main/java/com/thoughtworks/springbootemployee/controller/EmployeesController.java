package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeesController() {
        employees.add(new Employee(1, "Russel", 22, "Male", 1000));
        employees.add(new Employee(2, "Janley", 18, "Male", 50000));
    }


    @GetMapping
    public List<Employee> getAllEmployees() {

        return employees;
    }
}
