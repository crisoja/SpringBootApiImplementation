package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> findCompanies() {
        return companyMapper.toResponse(companyService.findCompanies());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest){
        return companyMapper.toResponse(companyService.addCompany(companyMapper.toEntity(companyRequest)));
    }

    @GetMapping("/{id}")
    public CompanyResponse findCompanyById(@PathVariable Integer id){
        return companyMapper.toResponse(companyService.findCompanyById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse updateCompany(@PathVariable Integer id, @RequestBody CompanyRequest company){
        return companyMapper.toResponse(companyService.updateCompany(id, companyMapper.toEntity(company)));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> findEmployeesByCompanyId(@PathVariable Integer id){
        return employeeMapper.toResponse(companyService.findEmployeesByCompanyId(id));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<CompanyResponse> findEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize){
        return companyMapper.toResponse(companyService.findCompaniesByPagination(pageIndex, pageSize));
    }

}
