package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
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
        return companyRepository.findById(id).orElse(null);
    }

    public Company updateCompany(Integer id, Company companyToUpdate) {
        Company company = findCompanyById(id);
        if(company == null){
            //will put exception later
        }
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
        if(company == null){
            //will put exception later
        }
        companyRepository.delete(company);
    }
}
