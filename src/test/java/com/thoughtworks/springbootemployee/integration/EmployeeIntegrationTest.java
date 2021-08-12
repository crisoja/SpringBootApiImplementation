package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    void should_return_all_employees_when_call_find_employees() throws Exception {
        // Given
        final Employee employee = new Employee(1, "russel", 22, "male", 5000);
        employeeRepository.save(employee);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("russel"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(5000));
    }

    @Test
    void should_add_employee_when_call_add_employee() throws Exception {
        //given
        String employee = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"barbielot\",\n" +
                "    \"age\": 13,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 1000\n" +
                "}";

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
        .andExpect(jsonPath("$.name").value("barbielot"))
        .andExpect(jsonPath("$.gender").value("male"))
        .andExpect(jsonPath("$.salary").value("1000"));
    }

    @Test
    void should_return_correct_employee_when_call_find_employee_by_id() throws Exception {
        //given
        final Employee employee = new Employee(1, "janley", 15, "male", 5000);
        employeeRepository.save(employee);
        //when and then
        int employeeId = 1;
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("janley"))
                .andExpect(jsonPath("$.age").value(15))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(5000));
    }

    @Test
    void should_return_list_of_employees_when_get_by_pagination_given_page_size_page_index() throws Exception {
        //given
        final Employee janley = new Employee(1, "janley", 15, "male", 5000);
        final Employee barbara = new Employee(2, "barbara", 18, "female", 50000);
        final Employee ismael = new Employee(3, "ismael", 18, "male", 30000);
        final Employee jakilu = new Employee(4, "jakilu", 18, "female", 80000);
        final Employee leonor = new Employee(5, "leonor", 18, "female", 70000);
        employeeRepository.save(janley);
        employeeRepository.save(barbara);
        employeeRepository.save(ismael);
        employeeRepository.save(jakilu);
        employeeRepository.save(leonor);

        //when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("pageIndex", "1")
                .param("pageSize","3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("janley"))
                .andExpect(jsonPath("$[1].name").value("barbara"))
                .andExpect(jsonPath("$[2].name").value("ismael"));
    }

    @Test
    void should_return_list_of_employees_with_male_gender_when_call_find_employee_by_gender() throws Exception {
        //given
        final Employee janley = new Employee(1, "janley", 15, "male", 5000);
        final Employee barbara = new Employee(2, "barbara", 18, "female", 50000);
        final Employee ismael = new Employee(3, "ismael", 18, "male", 30000);
        final Employee jakilu = new Employee(4, "jakilu", 18, "female", 80000);
        final Employee leonor = new Employee(5, "leonor", 18, "female", 70000);
        employeeRepository.save(janley);
        employeeRepository.save(barbara);
        employeeRepository.save(ismael);
        employeeRepository.save(jakilu);
        employeeRepository.save(leonor);

        //when and then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender={gender}", "male"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("janley"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[1].name").value("ismael"))
                .andExpect(jsonPath("$[1].gender").value("male"));

    }
}
