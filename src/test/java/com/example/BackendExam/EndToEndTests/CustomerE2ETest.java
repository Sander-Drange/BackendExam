package com.example.BackendExam.EndToEndTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllCustomers() throws Exception {
        mockMvc.perform(get("/api/customers")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCustomerById() throws Exception {
        // Assuming a customer with ID 1 exists
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

