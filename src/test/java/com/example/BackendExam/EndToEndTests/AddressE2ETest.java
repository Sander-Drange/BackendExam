package com.example.BackendExam.EndToEndTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AddressE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPreloadedAddresses() throws Exception {
        mockMvc.perform(get("/api/addresses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].street").value("Oslostreet"))
                .andExpect(jsonPath("$[0].city").value("Oslo"))
                .andExpect(jsonPath("$[0].country").value("Norway"))
                .andExpect(jsonPath("$[1].street").value("Swedenstreet"))
                .andExpect(jsonPath("$[1].city").value("Gothenburg"))
                .andExpect(jsonPath("$[1].country").value("Sweden"));
    }

    @Test
    public void testCreateAddress() throws Exception {
        String newAddressJson = "{\"street\":\"New Street\", \"streetNumber\": 123, \"city\":\"New City\", \"country\":\"Newland\"}";

        mockMvc.perform(post("/api/addresses")
               .contentType(MediaType.APPLICATION_JSON)
               .content(newAddressJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.street").value("New Street"))
               .andExpect(jsonPath("$.streetNumber").value(123));
    }

    @Test
    public void testGetAddress() throws Exception {
        mockMvc.perform(get("/api/addresses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").exists())
                .andExpect(jsonPath("$.city").exists())
                .andExpect(jsonPath("$.country").exists());
    }

    @Test
    public void testUpdateAddress() throws Exception {
        String updateAddressJson = "{\"street\":\"Updated Street\", \"streetNumber\": 456, \"city\":\"Updated City\", \"country\":\"Updatedland\"}";
        mockMvc.perform(put("/api/addresses/1")
               .contentType(MediaType.APPLICATION_JSON)
               .content(updateAddressJson))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.street").value("Updated Street"))
               .andExpect(jsonPath("$.streetNumber").value(456));
    }
}
