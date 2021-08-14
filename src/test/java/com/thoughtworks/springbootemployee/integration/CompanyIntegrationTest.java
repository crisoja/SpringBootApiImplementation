package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    public void tearDown(){
        companyRepository.deleteAll();
    }

    @Test
    void should_return_all_companies_when_find_api() throws Exception {
        final Company company = new Company("OOCL");
        companyRepository.save(company);

        mockMvc.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("OOCL"));
    }

    @Test
    void should_add_company_when_call_add_company_api() throws Exception {
        String company = "{\n" +
                "    \"companyName\": \"OOCL\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(company))
                .andExpect(jsonPath("$.companyName").value("OOCL"));
    }

    @Test
    void should_return_correct_company_when_call_find_company_by_id_api() throws Exception {
        //given
        final Company company = new Company(1,"OOCL");
        final Company findCompany =  companyRepository.save(company);
        //when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{id}", findCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("OOCL"));
    }

    @Test
    void should_update_company_when_call_update_company_api() throws Exception {
        //given
        final Company cosco = new Company(1,"COSCO");
        final Company company = companyRepository.save(cosco);
        String companyUpdate ="{\n" +
                "    \"companyName\": \"OOCL\"\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/companies/{id}", company.getId())
                .contentType(MediaType.APPLICATION_JSON).
                        content(companyUpdate))
                .andExpect(jsonPath("$.companyName").value("OOCL"));
    }

    @Test
    void should_delete_employee_when_call_delete_employee() throws Exception {
        //given
        final Company cosco = new Company("COSCO");
        final Company company = companyRepository.save(cosco);

        //when then
        mockMvc.perform(MockMvcRequestBuilders.delete("/companies/{id}", company.getId()))
                .andExpect(status().isOk());
    }
}

