package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private RetiringEmployeeRepository retiringEmployeeRepository;
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
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
        Employee employee = findEmployeeById(employeeId);

        return updateEmployeeInformation(employee, employeeUpdated);
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

    public void deleteEmployee(Integer employeeId) {
        Employee willBeDeletedEmployee = findEmployeeById(employeeId);
        employeeRepository.delete(willBeDeletedEmployee);
    }
}
