package com.example.BackendExam.EndToEndTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllCustomersWithPagination() throws Exception {
        int page = 0;
        int size = 2;

        mockMvc.perform(get("/api/customers")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.totalPages", greaterThanOrEqualTo(1)))
                .andExpect(jsonPath("$.totalElements", greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.size", greaterThanOrEqualTo(size)))
                .andExpect(jsonPath("$.number", equalTo(page)));
    }


    @Test
    public void testGetCustomerById() throws Exception {
        mockMvc.perform(get("/api/customers/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        String newCustomerJson = "{\"name\":\"John Doe\", \"email\":\"johndoe@example.com\", \"dob\":\"1990-01-01\"}";

        mockMvc.perform(post("/api/customers")
               .contentType(MediaType.APPLICATION_JSON)
               .content(newCustomerJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        String updatedCustomerJson = "{\"name\":\"Jane Doe\", \"email\":\"janedoe@example.com\", \"dob\":\"1991-01-01\"}";

        mockMvc.perform(put("/api/customers/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(updatedCustomerJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

}

