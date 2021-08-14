package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
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
    public Company addEmployee(@RequestBody Company company){
        return companyService.addEmployee(company);
    }

    @GetMapping("/{id}")
    public Company findCompanyById(@PathVariable Integer id){
        return companyService.findCompanyById(id);
    }
}
