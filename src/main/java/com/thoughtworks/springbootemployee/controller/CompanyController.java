package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> findCompanies() {
        return companyService.findCompanies();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company){
        return companyService.addCompany(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable Integer id){
        return companyService.findCompanyById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company company){
        return companyService.updateCompany(id,company);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }
    @GetMapping("/{id}/employees")
    public List<Employee> findEmployeesByCompanyId(@PathVariable Integer id){
        return companyService.findEmployeesByCompanyId(id);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Company> findEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return companyService.findCompaniesByPagination(pageIndex, pageSize);
    }

}
