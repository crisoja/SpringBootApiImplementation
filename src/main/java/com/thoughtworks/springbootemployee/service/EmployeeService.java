package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException("Employee ID not found!"));
    }

    public List<Employee> findEmployeeByGender(String gender){
        return employeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return employeeRepository.findAll(PageRequest.of((pageIndex - 1),pageSize)).getContent();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeUpdated) {
        return updateEmployeeInformation(findEmployeeById(employeeId), employeeUpdated);
    }

    private Employee updateEmployeeInformation(Employee employee, Employee employeeUpdated) {
        if (employeeUpdated.getAge() != null) {
            employee.setAge(employeeUpdated.getAge());
        }
        if (employeeUpdated.getName() != null) {
            employee.setName(employeeUpdated.getName());
        }
        if (employeeUpdated.getGender() != null) {
            employee.setGender(employeeUpdated.getGender());
        }
        if (employeeUpdated.getSalary() != null) {
            employee.setSalary(employeeUpdated.getSalary());
        }
        employeeRepository.save(employee);
        return employee;
    }

    public Employee deleteEmployee(Integer employeeId) {
        Employee willBeDeletedEmployee = findEmployeeById(employeeId);
        employeeRepository.delete(willBeDeletedEmployee);
        return willBeDeletedEmployee;
    }
}
