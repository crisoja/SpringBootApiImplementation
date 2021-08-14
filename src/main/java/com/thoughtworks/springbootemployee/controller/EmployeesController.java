package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private List<Employee> employees = new ArrayList<>();
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> findEmployees() {
        return employeeMapper.toResponse(employeeService.findEmployees());
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse findEmployeeById(@PathVariable Integer employeeId){
        return employeeMapper.toResponse(employeeService.findEmployeeById(employeeId));
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponse> findEmployeeByGender(@RequestParam String gender){
        return employeeMapper.toResponse(employeeService.findEmployeeByGender(gender));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<EmployeeResponse> findEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return employeeMapper.toResponse(employeeService.findEmployeesByPagination(pageIndex, pageSize));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employee) {
        return employeeMapper.toResponse(employeeService.addEmployee(employeeMapper.toEntity(employee)));
    }

    @PutMapping("/{employeeId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse updateEmployee(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return  employeeMapper.toResponse(employeeService.updateEmployee(employeeId, employeeMapper.toEntity(employeeRequest)));
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
        employeeMapper.toResponse(employeeService.deleteEmployee(employeeId));
    }
}
