package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findCompanies() {
        return companyRepository.findAll();
    }

    public Company addCompany(Company company) {
       return companyRepository.save(company);
    }

    public Company findCompanyById(Integer id){
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company ID not found!"));
    }

    public Company updateCompany(Integer id, Company companyToUpdate) {
        Company company = findCompanyById(id);
            return updateCompanyInformation(company, companyToUpdate);
    }

    private Company updateCompanyInformation(Company company, Company companyUpdated) {
        if (companyUpdated.getCompanyName() != null) {
            company.setCompanyName(companyUpdated.getCompanyName());
        }
        companyRepository.save(company);
        return company;
    }
    public void deleteCompany(Integer id) {
        Company company = findCompanyById(id);
        companyRepository.delete(company);
    }

    public List<Employee> findEmployeesByCompanyId(Integer id){
        Company company = findCompanyById(id);
       return companyRepository.findById(company.getId()).get().getEmployees();
    }

    public List<Company> findCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of((pageIndex - 1), pageSize)).getContent();
    }
}
