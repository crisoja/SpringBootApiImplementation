package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "russel", 22, "male", 1000));
        employees.add(new Employee(2, "janley", 18, "male", 50000));
        employees.add(new Employee(3, "barbie", 20, "female", 2000));
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
