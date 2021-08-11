package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        // Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "russel", 22, "male", 1000));
        employees.add(new Employee(2, "janley", 18, "male", 50000));
        employees.add(new Employee(3, "barbie", 20, "female", 2000));
        given(employeeRepository.getEmployees()).willReturn(employees);

        // When
        List<Employee> actualEmployees = employeeService.getEmployees();

        // Then
        assertEquals(employees, actualEmployees);
        assertIterableEquals(employees, actualEmployees);

    }

    @Test
    void should_return_specific_employee_when_get_employee_given_employee_id() {
        // Given
        Employee employee = new Employee(4, "ace", 21, "male", 500);
        when(employeeRepository.findEmployeeById(4)).thenReturn(employee);

        // When
        Employee actualEmployee = employeeService.findEmployeeById(4);

        // Then
        assertEquals(employee, actualEmployee);

    }

    @Test
    void should_return_employees_when_get_employee_by_gender_given_employee_gender() {
        // Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "russel", 22, "male", 1000));
        employees.add(new Employee(2, "janley", 18, "male", 50000));
        employees.add(new Employee(3, "barbie", 20, "female", 2000));
        List<Employee> maleEmployees = new ArrayList<>();
        when(employeeRepository.findEmployeeByGender("male")).thenReturn(maleEmployees);

        // When
        List<Employee> actualMaleEmployees = employeeService.findEmployeeByGender("male");

        // Then
        assertEquals(maleEmployees, actualMaleEmployees);
        assertIterableEquals(maleEmployees, actualMaleEmployees);

    }
}
