package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        when(employeeRepository.findEmployeeByGender("male")).thenReturn(employees);

        // When
        List<Employee> actualMaleEmployees = employeeService.findEmployeeByGender("male");

        // Then
        assertEquals(employees, actualMaleEmployees);
        assertIterableEquals(employees, actualMaleEmployees);

    }

    @Test
    void should_return_2_employee_when_get_by_page_given_2_page_size() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "russel", 22, "male", 1000));
        employees.add(new Employee(2, "janley", 18, "male", 50000));
        when(employeeRepository.getEmployeesByPagination(1,2)).thenReturn((employees));

        // When
        List<Employee> actualEmployees = employeeService.getEmployeesByPagination(1, 2);

        // Then
        assertEquals(employees, actualEmployees);
        assertIterableEquals(employees, actualEmployees);

    }

    @Test
    public void should_create_employee_when_add_given_one_employee() {
        // Given
        Employee employee = new Employee(1, "russel", 22, "male", 1000);
        when(employeeRepository.addEmployee(employee)).thenReturn(employee);

        // When
        Employee actualEmployee = employeeService.addEmployee(employee);

        // Then
        assertEquals(employee, actualEmployee);
    }

}
