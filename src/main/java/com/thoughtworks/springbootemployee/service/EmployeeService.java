package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployees() {
        return employeeRepository.findEmployees();
    }

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findEmployeeById(employeeId);
    }

    public List<Employee> findEmployeeByGender(String gender){
        return employeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return employeeRepository.getEmployeesByPagination(pageIndex, pageSize);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {
        return employeeRepository.updateEmployee(employeeId, employeeToBeUpdated);
    }

    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }
}
