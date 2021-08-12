package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return retiringEmployeeRepository.findEmployeeById(employeeId);
    }

    public List<Employee> findEmployeeByGender(String gender){
        return retiringEmployeeRepository.findEmployeeByGender(gender);
    }

    public List<Employee> findEmployeesByPagination(Integer pageIndex, Integer pageSize){
        return retiringEmployeeRepository.findEmployeesByPagination(pageIndex, pageSize);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer employeeId, Employee employeeToBeUpdated) {
        return retiringEmployeeRepository.updateEmployee(employeeId, employeeToBeUpdated);
    }

    public void deleteEmployee(Integer employeeId) {
        retiringEmployeeRepository.deleteEmployee(employeeId);
    }
}
