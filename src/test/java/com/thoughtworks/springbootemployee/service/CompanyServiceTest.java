package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
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

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private RetiringEmployeeRepository retiringEmployeeRepository;

    @Test
    void should_return_all_companies_when_find_companies() {
        // Given


    }
}